package com.omega.graphics;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Diálogo de arranque para nombrar y definir el directorio de trabajo del proyecto.
 */
public class LauncherDialog extends JDialog {
    private String projectName = "";
    private String projectPath = "";
    private boolean confirmed = false;

    public LauncherDialog(Frame owner) {
        super(owner, "Omega Engine - Nuevo Proyecto", true);
        setLayout(new BorderLayout(10, 10));
        setSize(450, 220);
        setLocationRelativeTo(owner);
        setResizable(false);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JTextField nameField = new JTextField("MiJuegoOmega");
        JTextField pathField = new JTextField(System.getProperty("user.home") + "/OmegaProjects");
        JButton browseBtn = new JButton("Examinar...");

        formPanel.add(new JLabel("Nombre del Proyecto:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Ruta en Disco:"));

        JPanel pathPanel = new JPanel(new BorderLayout(5, 0));
        pathPanel.add(pathField, BorderLayout.CENTER);
        pathPanel.add(browseBtn, BorderLayout.EAST);
        formPanel.add(pathPanel);

        browseBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                pathField.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        });

        JButton createBtn = new JButton("Inicializar y Abrir Editor");
        createBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        createBtn.addActionListener(e -> {
            this.projectName = nameField.getText().trim();
            this.projectPath = pathField.getText().trim();

            if (projectName.isEmpty() || projectPath.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            File dir = new File(projectPath, projectName);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            this.confirmed = true;
            dispose();
        });

        add(formPanel, BorderLayout.CENTER);
        add(createBtn, BorderLayout.SOUTH);
    }

    public boolean isConfirmed() { return confirmed; }
    public String getProjectName() { return projectName; }
    public String getProjectPath() { return projectPath; }
}
