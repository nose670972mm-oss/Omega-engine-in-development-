package com.omega.graphics;

import com.omega.core.Node;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/**
 * Ventana principal del Editor de Omega Engine.
 */
public class EditorWindow extends JFrame {
    private final Node rootNode;
    private JTree hierarchyTree;
    private JPanel viewportPanel;

    public EditorWindow(String projectName, Node rootNode) {
        super("Omega Engine Editor - " + projectName + " [Developer: Isaías]");
        this.rootNode = rootNode;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        // --- PANEL IZQUIERDO: Jerarquía de Nodos ---
        DefaultMutableTreeNode treeRoot = buildTreeModel(rootNode);
        DefaultTreeModel treeModel = new DefaultTreeModel(treeRoot);
        hierarchyTree = new JTree(treeModel);
        hierarchyTree.setBackground(new Color(0x28, 0x2C, 0x34));
        hierarchyTree.setForeground(Color.WHITE);

        JScrollPane hierarchyScroll = new JScrollPane(hierarchyTree);
        hierarchyScroll.setPreferredSize(new Dimension(250, 0));
        hierarchyScroll.setBorder(BorderFactory.createTitledBorder("Jerarquía de la Escena"));

        // --- PANEL CENTRAL: Lienzo Gráfico 2D ---
        viewportPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo estilo Grid
                g2d.setColor(new Color(0x1E, 0x1E, 0x24));
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // Cuadrícula guía
                g2d.setColor(new Color(0x2A, 0x2A, 0x32));
                for (int x = 0; x < getWidth(); x += 40) g2d.drawLine(x, 0, x, getHeight());
                for (int y = 0; y < getHeight(); y += 40) g2d.drawLine(0, y, getWidth(), y);

                // Renderizado recursivo de la jerarquía
                renderNode2D(g2d, rootNode, getWidth() / 2, getHeight() / 2);
            }
        };

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, hierarchyScroll, viewportPanel);
        splitPane.setDividerLocation(260);

        add(splitPane, BorderLayout.CENTER);
    }

    private DefaultMutableTreeNode buildTreeModel(Node node) {
        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(node.getName());
        for (Node child : node.getChildren()) {
            treeNode.add(buildTreeModel(child));
        }
        return treeNode;
    }

    private void renderNode2D(Graphics2D g2d, Node node, int parentX, int parentY) {
        int currentX = parentX + (int) node.transform.x;
        int currentY = parentY + (int) node.transform.y;

        if (!node.getName().equals("SceneRoot")) {
            g2d.setColor(new Color(0x61, 0xAF, 0xEF)); // Azul Omega
            int size = (int) (40 * node.transform.scaleX);
            g2d.fillRect(currentX - size / 2, currentY - size / 2, size, size);

            g2d.setColor(Color.WHITE);
            g2d.drawString(node.getName(), currentX - 20, currentY - size / 2 - 5);
        }

        for (Node child : node.getChildren()) {
            renderNode2D(g2d, child, currentX, currentY);
        }
    }

    public void refreshViewport() {
        viewportPanel.repaint();
    }
}
