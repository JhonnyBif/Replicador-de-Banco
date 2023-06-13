package graphic;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import execution.ReplicacaoExecutar;

import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Replicacao extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JFormattedTextField txt_tempoReplicacao;
	private JProgressBar progressBar;
	private int qtTotal;

	private Timer timer;
	private int contador = 10;
	
	/**
	 * Create the frame.
	 */
	public Replicacao() {
		
		NumberFormatter formatter = new NumberFormatter();
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setAllowsInvalid(false);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 340);
		contentPane = new JPanel();
		contentPane.setForeground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 50, 100, 70, 80, 20, 0};
		gbl_contentPane.rowHeights = new int[]{10, 20, 20, 20, 20, 20, 20, 20, 20, 10, 10, 10, 20, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Banco Origem");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 4;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Banco Destino");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 4;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 4;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 5;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Tempo de Intervalo de Replicação (s)");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 7;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		
		txt_tempoReplicacao = new JFormattedTextField(formatter);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.gridwidth = 4;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 8;
		contentPane.add(txt_tempoReplicacao, gbc_textField_2);
		txt_tempoReplicacao.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Selecionar");
		comboBox.addItem("Completo");
		comboBox.addItem("Parcial");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 10;
		contentPane.add(comboBox, gbc_comboBox);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				try {
		            int tempoReplicacao = Integer.parseInt(txt_tempoReplicacao.getText());
		            String tipoReplicacao = comboBox.getSelectedItem().toString();
		            boolean isParcial = tipoReplicacao.equals("Parcial");
		            ReplicacaoExecutar replicacaoExecutar = new ReplicacaoExecutar(Replicacao.this);
		            ReplicacaoExecutar.execute(tempoReplicacao * 1000, isParcial);
		            
		            
		        } catch (SQLException t) {
		            t.printStackTrace();
		        }

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 10;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.BOTH;
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 4;
		gbc_btnCancel.gridy = 10;
		contentPane.add(btnCancel, gbc_btnCancel);
		
		progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.BOTH;
		gbc_progressBar.gridwidth = 4;
		gbc_progressBar.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 12;
		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.darkGray);
		contentPane.add(progressBar, gbc_progressBar);
		
		
	}
	
	 public void startProgress() {
	        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
	            @Override
	            protected Void doInBackground() throws Exception {
	                for (int i = 0; i <= 100; i++) {
	                    Thread.sleep(33);

	                    publish(i); 

	                    if (isCancelled()) {
	                        break;
	                    }
	                }

	                return null;
	            }

	            @Override
	            protected void process(List<Integer> chunks) {
	                int progress = chunks.get(chunks.size() - 1);
	                progressBar.setValue(progress);
	            }

	            @Override
	            protected void done() {
	            	progressBar.setValue(100);
	            	iniciarContador();
	            }
	        };

	        worker.execute(); 
	    }
	 private void iniciarContador() {
		 Date dataAtual = new Date();
		    String mensagem = "Replicação concluida. Data: " + dataAtual.toString();
		    exibirMensagemTemporaria(mensagem, 2000);
	    }

	    private void exibirMensagemTemporaria(String mensagem, int tempoExibicao) {
	        JDialog dialog = new JDialog(this, "Mensagem", true);
	        dialog.setSize(500, 150);
	        dialog.setLocationRelativeTo(this);
	        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	        JLabel label = new JLabel(mensagem);
	        label.setHorizontalAlignment(SwingConstants.CENTER);
	        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        dialog.add(label);

	        Timer timer = new Timer(tempoExibicao, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                dialog.dispose(); // Fecha o diálogo após o tempo de exibição
	            }
	        });
	        progressBar.setValue(0);
	        timer.setRepeats(false);
	        timer.start();

	        dialog.setVisible(true);
	    }
	    
	public void setDataBaseOrigem(String nome) {
		this.textField.setText(nome);
	}
	
	public void setDataBaseDestino(String nome) {
		this.textField_1.setText(nome);
	}
			
}
