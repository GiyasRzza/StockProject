package StockProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class stockPanel  extends JFrame{
    private JPanel socketPanel;
    private JTextField productTxt;
    private JTextField priceTxt;
    private JTable productList;
    private JButton saveButton;
    private JLabel message;
    private JLabel category;
    private JLabel product;
    private JLabel price;
    private JComboBox catecoryCombo;
    private JButton updateButton;
    private JButton deleteButton;

    private void createTable(){
            String[] colName = {"Product Name","Category","Price"};
            productList.setModel(new DefaultTableModel(
                     null,
                    colName
            ));
            productList.setVisible(true);



        }
    public stockPanel() {
      add(socketPanel);
      setVisible(true);
      setSize(400,400);
      setTitle("Product Add");
        createTable();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message.setText("");
                DefaultTableModel model = (DefaultTableModel) productList.getModel();
                productList.getColumnModel();
                if (productTxt.getText().trim().isEmpty()){
                    message.setText("Mehsul adi bos ola bilmez!!");
                    JOptionPane.showMessageDialog(null,message);
                }
                else {
                    Object[] added = {productTxt.getText(),catecoryCombo.getSelectedItem().toString(),priceTxt.getText()};
                        model.addRow(added);
                        message.setText("Mehsul elava edildi!");




                }

            }
        });
        updateButton.addActionListener(e -> {
            message.setText("");
            DefaultTableModel defaultTableModel =  (DefaultTableModel) productList.getModel();
            int select = catecoryCombo.getSelectedIndex();
            if (select==-1){
                if (defaultTableModel.getRowCount()==0){
                    message.setText("Is Empty");

                }
                else {
                    message.setText("Guncellemek istediyniz setiri secin");
                    JOptionPane.showMessageDialog(null,message);

                }
            }
            else {
                defaultTableModel.setValueAt(productTxt.getText(),select,0);
                defaultTableModel.setValueAt(catecoryCombo.getSelectedItem().toString(),select,1);
                defaultTableModel.setValueAt(priceTxt.getText(),select,2);
                message.setText("Mehsul Deyisdirildi!!");
                JOptionPane.showMessageDialog(null,message);
            }
        });
        productList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel model = (DefaultTableModel) productList.getModel();
                int select = productList.getSelectedRow();
                productTxt.setText(model.getValueAt(select,0).toString());
                catecoryCombo.setSelectedItem(model.getValueAt(select,1));
                priceTxt.setText(model.getValueAt(select,2).toString());


            }
        });
        deleteButton.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) productList.getModel();
                int select = productList.getSelectedRow();
                if (select==-1){
                    if (model.getRowCount()==0){
                        message.setText("Bazada Melumat yoxudur!");
                        JOptionPane.showMessageDialog(null,message);
                    }
                    else {
                        message.setText("Zehmet olmasa Silmek istediyniz setri secin");
                        JOptionPane.showMessageDialog(null,message);
                    }
                }
                else {
                    model.removeRow(select);
                    message.setText("Deyer silindi!");
                }
        });
    }
}
