import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import org.w3c.dom.events.MouseEvent;

import javax.swing.JOptionPane;

import java.util.*;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;

public class TugasStudiKasus_223040161_HanaNurkarimah extends JFrame {
    tableModelTugasStudiKasus tableModelTugasStudiKasus;
    String jenisKelamin = "";

    public TugasStudiKasus_223040161_HanaNurkarimah()  {
        
        exitConfirmation();
        
        JLabel judulAplikasi = new JLabel("APLIKASI PENYIMPANAN BIODATA");
        Dimension size = judulAplikasi.getPreferredSize();
        judulAplikasi.setBounds(400, 50, size.width, size.height);

        // Input nama
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(10, 100, 100, 25);
        JTextField inputNama = new JTextField(20);
        inputNama.setBounds(10, 120, 300, 25);
        JLabel labelValidasiNama = new JLabel();
        labelValidasiNama.setBounds(10, 100, 300, 100);
        labelValidasiNama.setForeground(Color.RED);

        // Input Nomor Telepon
        JLabel labelNomorHp = new JLabel("Nomor HP:");
        labelNomorHp.setBounds(10, 160, 100, 25);
        JTextField inputNomorHp = new JTextField(20);
        inputNomorHp.setBounds(10, 180, 300, 25);
        JLabel labelValidasiNomorHP = new JLabel();
        labelValidasiNomorHP.setBounds(10, 200, 300, 25);
        labelValidasiNomorHP.setForeground(Color.RED);

        // Input alamat
        JLabel labelAlamat = new JLabel("Alamat:");
        labelAlamat.setBounds(10, 220, 100, 25);
        JTextArea inputAlamat = new JTextArea();
        inputAlamat.setBounds(10, 240, 300, 60);
        JLabel labelValidasiAlamat = new JLabel();
        labelValidasiAlamat.setBounds(10, 300, 300, 25);
        labelValidasiAlamat.setForeground(Color.RED);

        // Input Jenis Kelamin
        JLabel labelRadio = new JLabel("Jenis Kelamin:");
        labelRadio.setBounds(10, 320, 100, 25);
        JRadioButton radioButton1 = new JRadioButton("Perempuan");
        radioButton1.setBounds(10, 340, 100, 25);
        JRadioButton radioButton2 = new JRadioButton("Laki-laki");
        radioButton2.setBounds(10, 360, 100, 25);
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);
        JLabel labelValidasiJenisKelamin = new JLabel();
        labelValidasiJenisKelamin.setBounds(10, 380, 300, 25);
        labelValidasiJenisKelamin.setForeground(Color.RED);

        // Tombol Simpan
        JButton buttonSave = new JButton("Simpan");
        buttonSave.setBounds(10, 410, 100, 25);

        // Tombol Delete
        JButton buttonDelete = new JButton("Hapus");
        buttonDelete.setBounds(235, 650, 100, 25);

        // Tombol Edit
        JButton buttonEdit = new JButton("Edit");
        buttonEdit.setBounds(130, 650, 100, 25);

        // Tabel Data
        javax.swing.JTable tabel = new JTable();
        JScrollPane scrollableTable = new JScrollPane(tabel);
        scrollableTable.setBounds(350, 100, 630, 580);

        tableModelTugasStudiKasus tableModelTugasStudiKasus = new tableModelTugasStudiKasus();
        tabel.setModel(tableModelTugasStudiKasus);

        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = inputNama.getText();
                String nomorHP = inputNomorHp.getText();
                String alamat = inputAlamat.getText();

                if (radioButton1.isSelected()) {
                    jenisKelamin = radioButton1.getText();
                }
                if (radioButton2.isSelected()) {
                    jenisKelamin = radioButton2.getText();
                }

                if (!inputNama.getText().trim().isEmpty()) {
                    labelValidasiNama.setText("");
                } else {
                    labelValidasiNama.setText("Nama belum diisi!");
                }
                if (!inputNomorHp.getText().trim().isEmpty()) {
                    labelValidasiNomorHP.setText("");
                } else {
                    labelValidasiNomorHP.setText("Nomor HP belum diisi!");
                }
                if (!inputAlamat.getText().trim().isEmpty()) {
                    labelValidasiAlamat.setText("");
                } else {
                    labelValidasiAlamat.setText("Alamat belum diisi!");
                }
                if (jenisKelamin != "") {
                    labelValidasiJenisKelamin.setText("");
                }  else {
                    labelValidasiJenisKelamin.setText("Jenis Kelamin belum diisi!");
                }
                if (!inputNama.getText().trim().isEmpty() && !inputNomorHp.getText().trim().isEmpty() && !inputAlamat.getText().trim().isEmpty() && jenisKelamin != "") {
                    labelValidasiNama.setText("");
                    labelValidasiNomorHP.setText("");
                    labelValidasiAlamat.setText("");
                    labelValidasiJenisKelamin.setText("");

                    Integer konfirmasiSimpanData = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menyimpan data?", "Simpan Biodata", JOptionPane.YES_NO_OPTION);
                    if (konfirmasiSimpanData == JOptionPane.YES_OPTION) {
                        tableModelTugasStudiKasus.add(new ArrayList<>(Arrays.asList(nama, nomorHP, jenisKelamin, alamat)));

                        try {
                            FileWriter file = new FileWriter("C:\\Users\\HANA NURKARIMAH\\kuliah\\TugasStudiKasus_223040161_HanaNurkarimah\\biodata.txt");
                            file.write(
                                "---------------BIODATA---------------\n"+
                                    "Nama               :" + nama + "\n" +
                                    "Jenis Kelamin      :" + jenisKelamin + "\n" +
                                    "Alamat             :" + alamat + "\n" +
                                    "Nomor HP           :" + nomorHP + "\n"
                                );
                            file.close();
                            JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke file.");
                        } catch (IOException exception) {
                            // TODO: handle exception
                            exception.printStackTrace();
                        }
                    }
                    inputNama.setText("");
                    inputNomorHp.setText("");
                    inputAlamat.setText("");
                }
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabel.getSelectedRow();
                if (selectedRow >= 0) {
                    tableModelTugasStudiKasus.remove(selectedRow);
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih data!");
                }
            }
        });

        buttonEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabel.getSelectedRow();
                if (selectedRow >= 0) {
                    if (radioButton1.isSelected()) {
                        jenisKelamin = radioButton1.getText();
                    }
                    if (radioButton2.isSelected()) {
                        jenisKelamin = radioButton2.getText();
                    }
                    tableModelTugasStudiKasus.setValueAt(inputNama.getText(), selectedRow, 0);
                    tableModelTugasStudiKasus.setValueAt(inputNomorHp.getText(), selectedRow, 1);
                    tableModelTugasStudiKasus.setValueAt(jenisKelamin, selectedRow, 2);
                    tableModelTugasStudiKasus.setValueAt(inputAlamat.getText(), selectedRow, 3);

                    inputNama.setText("");
                    inputNomorHp.setText("");
                    inputAlamat.setText("");

                    JOptionPane.showMessageDialog(null, "Data berhasil diedit");
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih data!");
                }
            }
        });

        tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // TODO Auto-generated method stub
                int selectedRow = tabel.getSelectedRow();

                inputNama.setText(tableModelTugasStudiKasus.getValueAt(selectedRow, 0).toString());
                inputNomorHp.setText(tableModelTugasStudiKasus.getValueAt(selectedRow, 1).toString());
                jenisKelamin = tableModelTugasStudiKasus.getValueAt(selectedRow, 2).toString();
                if (jenisKelamin.equals("Perempuan")) {
                    radioButton1.setSelected(true);
                } else {
                    radioButton2.setSelected(true);
                }
                inputAlamat.setText(tableModelTugasStudiKasus.getValueAt(selectedRow, 3).toString());
            }
        });

        this.add(labelNama);
        this.add(inputNama);
        this.add(labelValidasiNama);
        this.add(labelNomorHp);
        this.add(inputNomorHp);
        this.add(labelValidasiNomorHP);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelValidasiJenisKelamin);
        this.add(labelAlamat);
        this.add(inputAlamat);
        this.add(labelValidasiAlamat);
        inputAlamat.setLineWrap(true);
        this.add(buttonSave);
        this.add(scrollableTable);
        this.add(judulAplikasi);
        this.add(buttonDelete);
        this.add(buttonEdit);
        this.setSize(1000,1000);
        this.setLayout(null);
    }

    public void exitConfirmation() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                int exit = JOptionPane.showConfirmDialog(null, "Anda yakin ingin keluar?", "Keluar", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (exit == JOptionPane.YES_OPTION) {
                    e.getWindow().dispose();
                    System.out.println("Keluar");
                } else {
                    System.out.println("Aplikasi masih berjalan");
                }
            }
        });
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TugasStudiKasus_223040161_HanaNurkarimah h = new TugasStudiKasus_223040161_HanaNurkarimah();
                h.setVisible(true);
            }
        });
    }
}