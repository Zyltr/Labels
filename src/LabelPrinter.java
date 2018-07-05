import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class LabelPrinter implements Printable
{
    private String labelString;
    private Font font;

    void setLabelString ( String labelString )
    {
        this.labelString = labelString;
    }

    void setFont ( Font font )
    {
        this.font = font;
    }

    @Override
    public int print ( Graphics graphics, PageFormat pageFormat, int pageIndex ) throws PrinterException
    {
        if ( pageIndex > 0 )
        {
            return NO_SUCH_PAGE;
        }

        System.out.println ( "LabelPrinter Font : " + font );
        System.out.println ( "LabelPrinter String : " + labelString );
        System.out.println ( );

        Graphics2D graphics2D = ( Graphics2D ) graphics;

        double width =  pageFormat.getWidth ();
        double height =  pageFormat.getHeight ();

        double x =  pageFormat.getImageableX ();
        double y =  pageFormat.getImageableY ();

        double iWidth =  pageFormat.getImageableWidth ();
        double iHeight =  pageFormat.getImageableHeight ();

        System.out.println ( "LabelPrinter X : " + x );
        System.out.println ( "LabelPrinter Y : " + y );
        System.out.println ( "LabelPrinter Width : " + width );
        System.out.println ( "LabelPrinter Height : " + height );
        System.out.println ( "LabelPrinter I-Width : " + iWidth );
        System.out.println ( "LabelPrinter I-Height : " +iHeight );


        System.out.println ( "Page Index : " + pageIndex );

        // Draw Rect Minimum Page for Labels; Varies by Printer
        graphics2D.setColor ( Color.GRAY );
        Rectangle2D page = new Rectangle2D.Double ( x, y, iWidth, iHeight );
        graphics2D.draw ( page );

        // Find differences in X and Y values
        double desiredX = 13.5;
        double desiredY = 36;

        x += ( desiredX - x );
        y += ( desiredY - y );

        // Calculate New I-Width and I-Height
        iWidth = width - 2 * x;
        iHeight = height - 2 * y;

        System.out.println ( "LabelPrinter New X : " + x );
        System.out.println ( "LabelPrinter New Y : " + y );
        System.out.println ( "LabelPrinter New I-Width : " + iWidth );
        System.out.println ( "LabelPrinter New I-Height : " +iHeight );

        // Begin Drawing Label Border
        graphics2D.setColor ( Color.RED );
        Rectangle2D border = new Rectangle2D.Double ( x, y, iWidth, iHeight );
        graphics2D.draw ( border );

        // Begin Drawing Label Boundaries
        double numberOfColumns = 3;
        double numberOfRows = 10;
        double labelWidth = iWidth / numberOfColumns;
        double labelHeight = iHeight / numberOfRows;

        double labelX = x, labelY = y;

        // Set Graphics Font and Get Font Metrics
        graphics2D.setFont ( font );

        FontMetrics fontMetrics = graphics2D.getFontMetrics ( font );
        Rectangle2D stringBounds = fontMetrics.getStringBounds ( labelString, graphics2D );

        double stringWidth = stringBounds.getWidth ();
        double offsetX = ( labelWidth - stringWidth ) / 2;
        double stringX = labelX + offsetX;

        double stringHeight = stringBounds.getHeight ();
        double offsetY = ( labelHeight - stringHeight ) / 2;
        double stringY = labelY + offsetY;

        for ( int row = 0; row < numberOfRows; row++ )
        {
            for ( int col = 0; col < numberOfColumns; col++ )
            {
                // Create Rounded Rectangle and Draw
                graphics2D.setColor ( Color.RED );
                RoundRectangle2D label = new RoundRectangle2D.Double ( labelX, labelY, labelWidth, labelHeight, 10, 10 );
                graphics2D.draw ( label );

                // Draw Label String
                graphics2D.setColor ( Color.BLACK );
                graphics2D.drawString ( labelString, ( float ) stringX, ( float ) stringY );

                // Increment LabelX Coordinate
                labelX += labelWidth;
                stringX = labelX + offsetX;
            }

            // Reset LabelX and StringX
            labelX = x;
            stringX = labelX + offsetX;

            // Increment LabelY
            labelY += labelHeight;
            stringY = labelY + offsetY;
        }

        System.out.println ();

        return PAGE_EXISTS;
    }
}
