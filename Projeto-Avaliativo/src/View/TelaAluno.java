package View;

import Controller.ControllerAluno;
import Models.Aluno;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TelaAluno extends JFrame {

    private final ControllerAluno controller;
    private DefaultTableModel tableModel;

    private JTextField txtId, txtNome, txtEndereco, txtTelefone, txtEmail, txtMatricula, txtNomePai, txtNomeMae;

    private static final Font FONTE_LABEL = new Font("Segoe UI", Font.BOLD, 12);
    private static final Font FONTE_TITULO_CRUD = new Font("Segoe UI", Font.BOLD, 20);

    public TelaAluno(ControllerAluno controller) {
        this.controller = controller;

        setTitle("Gerenciar Alunos (CRUD)");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240));
        setLayout(new BorderLayout(10, 10));

        // 1. Título
        JLabel titulo = new JLabel("Cadastro e Consulta de Alunos", SwingConstants.CENTER);
        titulo.setFont(FONTE_TITULO_CRUD);
        titulo.setBorder(new EmptyBorder(15, 0, 15, 0));
        add(titulo, BorderLayout.NORTH);

        // 2. Painel de Input
        JPanel inputPanel = createInputPanel();
        
        // 3. Painel de Botões
        JPanel buttonPanel = createButtonPanel();

        // 4. Painel de Tabela
        JScrollPane tableScrollPane = createTablePanel();
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
        add(tableScrollPane, BorderLayout.SOUTH);

        // Se o controller for nulo, este método falha, por isso verificamos a injeção no Main.java
        loadTableData(); 
    }

    private JPanel createInputPanel() {
        // ... (código GridBagLayout para campos de texto) ...
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Dados do Aluno"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtId = new JTextField(5);
        txtId.setEditable(true);

        txtNome = new JTextField(20);
        txtEndereco = new JTextField(20);
        txtTelefone = new JTextField(15);
        txtEmail = new JTextField(20);
        txtMatricula = new JTextField(10);
        txtNomePai = new JTextField(20);
        txtNomeMae = new JTextField(20);

        int row = 0;
        row = addField(panel, gbc, "ID:", txtId, row, 0);
        row = addField(panel, gbc, "Nome:", txtNome, row, 0);
        row = addField(panel, gbc, "Endereço:", txtEndereco, row, 0);
        row = addField(panel, gbc, "Telefone:", txtTelefone, row, 0);
        row = addField(panel, gbc, "Email:", txtEmail, row, 0);
        row = addField(panel, gbc, "Matrícula:", txtMatricula, row, 0);
        row = addField(panel, gbc, "Nome Pai:", txtNomePai, row, 0);
        row = addField(panel, gbc, "Nome Mãe:", txtNomeMae, row, 0);

        return panel;
    }
    
    private int addField(JPanel panel, GridBagConstraints gbc, String labelText, JTextField textField, int row, int col) {
        JLabel label = new JLabel(labelText);
        label.setFont(FONTE_LABEL);
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.weightx = 0.0;
        panel.add(label, gbc);

        gbc.gridx = col + 1;
        gbc.weightx = 1.0;
        panel.add(textField, gbc);
        return row + 1;
    }
    
    private JPanel createButtonPanel() {
        // ... (código para botões) ...
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));

        JButton btnSave = new JButton("Salvar");
        JButton btnFind = new JButton("Buscar por ID");
        JButton btnDelete = new JButton("Deletar");
        JButton btnClear = new JButton("Limpar");

        btnSave.addActionListener(e -> saveAluno());
        btnFind.addActionListener(e -> findAluno());
        btnDelete.addActionListener(e -> deleteAluno());
        btnClear.addActionListener(e -> clearFields());

        panel.add(btnSave);
        panel.add(btnFind);
        panel.add(btnDelete);
        panel.add(btnClear);
        
        return panel;
    }

    private JScrollPane createTablePanel() {
        // ... (código para a tabela) ...
        String[] columnNames = {"ID", "Nome", "Matrícula", "Email", "Telefone", "Endereço"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                populateFieldsFromTable(table.getSelectedRow());
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(750, 300));
        return scrollPane;
    }

    private void saveAluno() {
        try {
            Integer id = Integer.parseInt(txtId.getText());
            
            List<Object> dados = Arrays.asList(
                id, 
                txtNome.getText(), 
                txtEndereco.getText(), 
                txtTelefone.getText(), 
                txtEmail.getText(), 
                txtMatricula.getText(), 
                txtNomePai.getText(), 
                txtNomeMae.getText()
            );

            if (controller.save(dados)) {
                JOptionPane.showMessageDialog(this, "Aluno salvo/atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Erro: Verifique se todos os campos estão preenchidos corretamente.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O campo ID deve ser um número inteiro.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void findAluno() {
        try {
            Integer id = Integer.parseInt(txtId.getText());
            Optional<Aluno> alunoOpt = controller.findById(id);

            if (alunoOpt.isPresent()) {
                Aluno a = alunoOpt.get();
                txtNome.setText(a.getNome());
                txtEndereco.setText(a.getEndereco());
                txtTelefone.setText(a.getTelefone());
                txtEmail.setText(a.getEmail());
                txtMatricula.setText(a.getMatricula());
                txtNomePai.setText(a.getNome_pai());
                txtNomeMae.setText(a.getNome_mae());
                JOptionPane.showMessageDialog(this, "Aluno encontrado!", "Busca", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Aluno com ID " + id + " não encontrado.", "Busca", JOptionPane.WARNING_MESSAGE);
                clearFieldsExceptId();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O ID para busca deve ser um número inteiro.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteAluno() {
        try {
            Integer id = Integer.parseInt(txtId.getText());
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Tem certeza que deseja deletar o Aluno com ID " + id + "?", 
                "Confirmar Deleção", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (controller.delete(id)) {
                    JOptionPane.showMessageDialog(this, "Aluno deletado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    loadTableData();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro: Aluno com ID " + id + " não encontrado para deleção.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O ID para deleção deve ser um número inteiro.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        List<Aluno> alunos = controller.findAll();

        for (Aluno a : alunos) {
            tableModel.addRow(new Object[]{
                a.getId(), 
                a.getNome(), 
                a.getMatricula(), 
                a.getEmail(), 
                a.getTelefone(), 
                a.getEndereco()
            });
        }
    }

    private void clearFields() {
        txtId.setText("");
        clearFieldsExceptId();
    }
    
    private void clearFieldsExceptId() {
        txtNome.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtMatricula.setText("");
        txtNomePai.setText("");
        txtNomeMae.setText("");
    }
    
    private void populateFieldsFromTable(int rowIndex) {
        try {
            Integer id = (Integer) tableModel.getValueAt(rowIndex, 0);
            Optional<Aluno> alunoOpt = controller.findById(id);
            
            if (alunoOpt.isPresent()) {
                Aluno a = alunoOpt.get();
                txtId.setText(String.valueOf(a.getId()));
                txtNome.setText(a.getNome());
                txtEndereco.setText(a.getEndereco());
                txtTelefone.setText(a.getTelefone());
                txtEmail.setText(a.getEmail());
                txtMatricula.setText(a.getMatricula());
                txtNomePai.setText(a.getNome_pai());
                txtNomeMae.setText(a.getNome_mae());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados da linha: " + e.getMessage(), "Erro UI", JOptionPane.ERROR_MESSAGE);
        }
    }
}