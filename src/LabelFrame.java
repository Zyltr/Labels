import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
/*
 * Created by JFormDesigner on Fri Jun 29 22:20:18 PDT 2018
 */


/**
 * @author Erik Huerta
 */
public class LabelFrame extends JFrame
{
    private PrinterJob printerJob = PrinterJob.getPrinterJob ();
    private LabelPrinter labelPrinter = new LabelPrinter ();
    private FontChooser fontChooser = new FontChooser ( this );
    private Font selectedFont = new Font ( "Helvetica Neue", Font.PLAIN, 12 );

    private LabelFrame ()
    {
        initComponents ();

        PageFormat pageFormat = printerJob.defaultPage (); // new PageFormat ();

        System.out.println ( "Default Page Format Width : " + pageFormat.getWidth () );
        System.out.println ( "Default Page Format Height : " + pageFormat.getHeight () );
        System.out.println ( "Default Page Format I-Width : " + pageFormat.getImageableWidth () );
        System.out.println ( "Default Page Format I-Height : " + pageFormat.getImageableHeight () );

        Paper usPaper = new Paper ();

        System.out.println ( "US-Letter Width : " + usPaper.getWidth () );
        System.out.println ( "US-Letter Height : " + usPaper.getHeight () );
        System.out.println ( "US-Letter I-Width : " + usPaper.getImageableWidth () );
        System.out.println ( "US-Letter I-Height : " + usPaper.getImageableHeight () );

        usPaper.setImageableArea ( 0, 0, 612, 792 );

        System.out.println ( "New US-Letter I-Width : " + usPaper.getImageableWidth () );
        System.out.println ( "New US-Letter I-Height : " + usPaper.getImageableHeight () );
        System.out.println ();

        pageFormat.setPaper ( usPaper );

        // Set Printable to LabelPrinter
        printerJob.setPrintable ( labelPrinter, pageFormat );
    }

    private void labelButtonActionPerformed ( ActionEvent actionEvent )
    {
        // TODO add your code here
        if ( actionEvent.getSource ().equals ( labelButton ) || actionEvent.getSource ().equals ( previewButton ) )
        {
            boolean doPrint = printerJob.printDialog ();

            if ( doPrint )
            {
                try
                {
                    labelPrinter.setPreview ( actionEvent.getSource ().equals ( previewButton ) );

                    // Set LabelPrinter String and Font Attributes
                    labelPrinter.setLabelString ( labelTextArea.getText () );
                    labelPrinter.setFont ( selectedFont );

                    printerJob.print ();
                }
                catch ( PrinterException printerException )
                {
                    printerException.printStackTrace ();
                }
            }
        }
    }

    private void fontButtonActionPerformed ( ActionEvent actionEvent )
    {
        // TODO add your code here
        if ( actionEvent.getSource ().equals ( fontButton ) )
        {
            fontChooser.setVisible ( true );

            selectedFont = fontChooser.getSelectedFont ();

            if ( selectedFont != null )
            {
                fontTextArea.setText ( selectedFont.getFontName () + " " + selectedFont.getSize () );
                fontTextArea.setFont ( selectedFont );
            }

            fontChooser.dispose ();
        }
    }


    public static void main ( String[] args )
    {
        SwingUtilities.invokeLater ( () -> new LabelFrame ().setVisible ( true ) );
    }

    private void initComponents ()
    {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner non-commercial license
		JLabel labelLabel = new JLabel();
		JScrollPane labelScrollPane = new JScrollPane();
		labelTextArea = new JTextArea();
		JLabel fontLabel = new JLabel();
		JLabel templateLabel = new JLabel();
		JComboBox<String> templateComboBox = new JComboBox<>();
		labelButton = new JButton();
		fontButton = new JButton();
		previewButton = new JButton();
		JScrollPane fontScrollPane = new JScrollPane();
		fontTextArea = new JTextArea();

		//======== this ========
		setResizable(false);
		Container contentPane = getContentPane();

		//---- labelLabel ----
		labelLabel.setText("Label String");
		labelLabel.setFont(new Font("Helvetica Neue", Font.BOLD | Font.ITALIC, 12));

		//======== labelScrollPane ========
		{
			labelScrollPane.setBorder(new LineBorder(Color.lightGray));

			//---- labelTextArea ----
			labelTextArea.setBorder(null);
			labelTextArea.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
			labelScrollPane.setViewportView(labelTextArea);
		}

		//---- fontLabel ----
		fontLabel.setText("Font");
		fontLabel.setFont(new Font("Helvetica Neue", Font.BOLD | Font.ITALIC, 12));

		//---- templateLabel ----
		templateLabel.setText("Template");
		templateLabel.setFont(new Font("Helvetica Neue", Font.BOLD | Font.ITALIC, 12));

		//---- templateComboBox ----
		templateComboBox.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		templateComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
			"Avery US-Letter"
		}));

		//---- labelButton ----
		labelButton.setText("Generate Labels");
		labelButton.setFont(new Font("Helvetica Neue", Font.BOLD | Font.ITALIC, 12));
		labelButton.addActionListener(e -> labelButtonActionPerformed(e));

		//---- fontButton ----
		fontButton.setText("Change Font");
		fontButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		fontButton.addActionListener(e -> fontButtonActionPerformed(e));

		//---- previewButton ----
		previewButton.setText("Preview");
		previewButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		previewButton.addActionListener(e -> labelButtonActionPerformed(e));

		//======== fontScrollPane ========
		{
			fontScrollPane.setBorder(new LineBorder(Color.lightGray));

			//---- fontTextArea ----
			fontTextArea.setBorder(null);
			fontTextArea.setText("Helvetica Neue 12 Plain");
			fontScrollPane.setViewportView(fontTextArea);
		}

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(15, 15, 15)
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(labelScrollPane, GroupLayout.Alignment.LEADING)
								.addComponent(labelButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
										.addComponent(templateComboBox, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
										.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
											.addComponent(labelLabel)
											.addGap(0, 0, Short.MAX_VALUE))
										.addComponent(fontScrollPane, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
									.addGap(18, 18, 18)
									.addGroup(contentPaneLayout.createParallelGroup()
										.addComponent(fontButton)
										.addComponent(previewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
							.addGap(15, 15, 15))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(fontLabel)
								.addComponent(templateLabel))
							.addContainerGap(330, Short.MAX_VALUE))))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(15, 15, 15)
					.addComponent(labelLabel)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(labelScrollPane, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(fontLabel)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addComponent(fontButton)
							.addGap(0, 57, Short.MAX_VALUE))
						.addComponent(fontScrollPane, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(templateLabel)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(templateComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(previewButton))
					.addGap(18, 18, 18)
					.addComponent(labelButton)
					.addGap(20, 20, 20))
		);
		pack();
		setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner non-commercial license
	private JTextArea labelTextArea;
	private JButton labelButton;
	private JButton fontButton;
	private JButton previewButton;
	private JTextArea fontTextArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
