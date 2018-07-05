import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
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
		System.out.println ( );

		pageFormat.setPaper ( usPaper );

		// Set Printable to LabelPrinter
		printerJob.setPrintable ( labelPrinter, pageFormat );
    }

    private void labelButtonActionPerformed ( ActionEvent actionEvent )
    {
        // TODO add your code here
        if ( actionEvent.getSource ().equals ( labelButton ) )
        {
            boolean doPrint = printerJob.printDialog ();

            if ( doPrint )
            {
                try
                {
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
				selectedFontLabel.setText ( selectedFont.getFontName () + " " + selectedFont.getSize () );
				selectedFontLabel.setFont ( selectedFont );
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
		selectedFontLabel = new JLabel();
		fontButton = new JButton();

		//======== this ========
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

		//---- selectedFontLabel ----
		selectedFontLabel.setText("Helvetica Neue 12 Plain");
		selectedFontLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));

		//---- fontButton ----
		fontButton.setText("Change Font");
		fontButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		fontButton.addActionListener(e -> fontButtonActionPerformed(e));

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(15, 15, 15)
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(fontLabel)
								.addComponent(templateLabel))
							.addContainerGap(330, Short.MAX_VALUE))
						.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(labelButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
								.addComponent(templateComboBox, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addComponent(selectedFontLabel, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(fontButton))
								.addComponent(labelScrollPane, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
								.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
									.addComponent(labelLabel)
									.addGap(0, 0, Short.MAX_VALUE)))
							.addGap(15, 15, 15))))
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
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(fontButton)
						.addComponent(selectedFontLabel))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(templateLabel)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(templateComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(labelButton)
					.addContainerGap())
		);
		pack();
		setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner non-commercial license
	private JTextArea labelTextArea;
	private JButton labelButton;
	private JLabel selectedFontLabel;
	private JButton fontButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
