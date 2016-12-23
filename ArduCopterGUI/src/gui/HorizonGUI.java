package gui;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

public class HorizonGUI extends JPanel {

	/**
	 * HorizonGui
	 */
	private static final long serialVersionUID = -1989295753245590218L;
	private GeneralPath bankMarkerLong;
	private GeneralPath bankMarkerShort;
	/****************************
	 * Defining instance variables
	 ****************************/
	private final Color blueSky;
	private Shape Cardinaldirection;
	private final Point2D centerPoint;
	private GeneralPath centerShape;

	private int dimMarker10Deg;
	private int dimMarker5Deg;

	private final Arc2D lowerArc; // Bottom part of the Horizon
	private Line2D markerLine;
	private final Color orangeEarth;
	private GradientPaint outline;
	private int pitchAngle;
	private final int radius;

	private int rollAngle;

	private Ellipse2D roundHorizon;
	private GeneralPath triangle;

	private final Arc2D upperArc; // Upper part of the Horizon
	private final Font writing = null;
	private int yawAngle;

	/************************************
	 * This constructor will create the initial panel for the Horizon
	 ************************************/

	public HorizonGUI() {
		setBackground(Color.black);

		// Define color used
		blueSky = new Color(10, 112, 156);
		orangeEarth = new Color(222, 132, 14);

		// Creates two arcs used to draw the outline
		upperArc = new Arc2D.Float();
		lowerArc = new Arc2D.Float();

		// Define a center point as a reference
		centerPoint = new Point2D.Float(150, 150);

		// Instance variables initialization
		radius = 100;

	}

	// public Dimension getPreferredSize(){
	// dimPanel = new Dimension();//500, 500);
	// return dimPanel;
	// }

	private void drawBankRollMarker(final Graphics2D g2d) {

		// Draw the line markers for bank angle
		bankMarkerLong = new GeneralPath(Path2D.WIND_EVEN_ODD);
		bankMarkerLong.moveTo(centerPoint.getX() - radius, centerPoint.getY());
		bankMarkerLong.lineTo(centerPoint.getX() - radius + 20,
				centerPoint.getY());

		bankMarkerShort = new GeneralPath(Path2D.WIND_EVEN_ODD);
		bankMarkerShort.moveTo(centerPoint.getX() - radius, centerPoint.getY());
		bankMarkerShort.lineTo(centerPoint.getX() - radius + 10,
				centerPoint.getY());

		for (int i = 0; i < 5; i++) {
			final AffineTransform ata = AffineTransform.getRotateInstance(
					Math.toRadians(30), centerPoint.getX(), centerPoint.getY());
			g2d.transform(ata);

			g2d.draw(bankMarkerLong);

		}

		AffineTransform ata = AffineTransform.getRotateInstance(
				Math.toRadians(260), centerPoint.getX(), centerPoint.getY());
		g2d.transform(ata);

		for (int i = 0; i < 7; i++) {
			final AffineTransform atb = AffineTransform.getRotateInstance(
					Math.toRadians(10), centerPoint.getX(), centerPoint.getY());
			g2d.transform(atb);

			g2d.draw(bankMarkerShort);
		}

		ata = AffineTransform.getRotateInstance(Math.toRadians(110),
				centerPoint.getX(), centerPoint.getY());
		g2d.transform(ata);

		for (int i = 0; i < 7; i++) {
			final AffineTransform atb = AffineTransform.getRotateInstance(
					Math.toRadians(10), centerPoint.getX(), centerPoint.getY());
			g2d.transform(atb);

			g2d.draw(bankMarkerShort);
		}
	}

	private void drawBankRollTriangle(final Graphics2D g2d) {

		// Draw the triangle on the upper position
		triangle = new GeneralPath(Path2D.WIND_EVEN_ODD);
		triangle.moveTo(centerPoint.getX(), centerPoint.getY() - radius + 5);
		triangle.lineTo(centerPoint.getX() - 15, centerPoint.getY() - radius
				+ 30);
		triangle.lineTo(centerPoint.getX() + 15, centerPoint.getY() - radius
				+ 30);
		triangle.closePath();

		g2d.fill(triangle);

		// Draw the triangle in the lower position
		triangle.moveTo(centerPoint.getX(), centerPoint.getY() + radius - 5);
		triangle.lineTo(centerPoint.getX() - 10, centerPoint.getY() + radius
				- 25);
		triangle.lineTo(centerPoint.getX() + 10, centerPoint.getY() + radius
				- 25);
		triangle.closePath();

		g2d.draw(triangle);
	}

	private void drawBCardinaldirectionlable(final Graphics2D g2d) {
		final AffineTransform at = AffineTransform.getRotateInstance(
				Math.toRadians(60), centerPoint.getX(), centerPoint.getY());
		g2d.transform(at);
		g2d.setPaint(Color.black);
		g2d.drawString(Messages.getString("HorizonGUI.0"), 135, 40); //$NON-NLS-1$
		g2d.drawString(Messages.getString("HorizonGUI.1"), 260, 155); //$NON-NLS-1$
		g2d.drawString(Messages.getString("HorizonGUI.2"), 135, 270); //$NON-NLS-1$
		g2d.drawString(Messages.getString("HorizonGUI.3"), 5, 155); //$NON-NLS-1$

		// Display the outline of the Horizon
		roundHorizon = new Ellipse2D.Float(50, 50, 2 * radius, 2 * radius);
		outline = new GradientPaint(20, 20, Color.white, 500, 500, Color.gray,
				true);
		g2d.setPaint(outline);
		g2d.setStroke(new BasicStroke(6));

		g2d.draw(roundHorizon);

	}

	private void drawCardinaldirection(final Graphics2D g2d) {

		// while (angle < 360) {
		Cardinaldirection = // x, y, w, h, start, extend, type
		new Arc2D.Double(50, 50, 200, 200, 89, 1, Arc2D.PIE);
		final AffineTransform at = AffineTransform.getRotateInstance(
				Math.toRadians(yawAngle), centerPoint.getX(),
				centerPoint.getY());

		g2d.transform(at);
		g2d.setPaint(Color.white);
		g2d.draw(Cardinaldirection);// }

	}

	private void drawHorizon(final Graphics2D g2d) {

		// Start doing some math calculation for angles
		int angStartUpper = 0;
		int angExtUpper = 0;
		final int angStartLower = 0;
		final int angExtLower = 360;

		// First step is to determine the roll display position
		AffineTransform at = AffineTransform.getRotateInstance(
				Math.toRadians(rollAngle), centerPoint.getX(),
				centerPoint.getY());
		g2d.transform(at);

		if (pitchAngle < 90 && pitchAngle > -90) {
			angStartUpper = -pitchAngle; // Minus because of the reverse
											// way of
											// working of the artificial
											// horizon
											// positive values let the blue
											// arc
											// to get bigger...
			angExtUpper = 180 - 2 * angStartUpper;
		}

		if (pitchAngle >= 90 && pitchAngle < 180) {
			at = AffineTransform.getRotateInstance(Math.toRadians(180),
					centerPoint.getX(), centerPoint.getY());
			g2d.transform(at);

			angStartUpper = -(180 - pitchAngle); // Minus because of the
													// reverse
													// way of working of the
													// artificial horizon
													// positive values let
													// the
													// blue arc to get
													// bigger...
			angExtUpper = 180 - 2 * angStartUpper;
		}

		if (pitchAngle <= -90 && pitchAngle > -180) {
			at = AffineTransform.getRotateInstance(Math.toRadians(180),
					centerPoint.getX(), centerPoint.getY());
			g2d.transform(at);

			angStartUpper = 180 + pitchAngle; // Minus because of the
												// reverse
			// way of working of the
			// artificial horizon positive
			// values let the blue arc to
			// get bigger...
			angExtUpper = 180 - 2 * angStartUpper;
		}

		// Draw the artificial horizon itself, composed by 2 half arcs
		lowerArc.setArcByCenter(centerPoint.getX(), centerPoint.getY(), radius,
				angStartLower, angExtLower, Arc2D.CHORD);
		g2d.setPaint(orangeEarth);
		g2d.fill(lowerArc);

		upperArc.setArcByCenter(centerPoint.getX(), centerPoint.getY(), radius,
				angStartUpper, angExtUpper, Arc2D.CHORD);
		g2d.setPaint(blueSky);
		g2d.fill(upperArc);

		// Draw the middle white line
		g2d.setStroke(new BasicStroke(1));
		g2d.setPaint(Color.white);
		g2d.draw(upperArc);

		drawMarkers(g2d);

		at = AffineTransform.getRotateInstance(Math.toRadians(-rollAngle),
				centerPoint.getX(), centerPoint.getY());

		g2d.transform(at);

	}

	private void drawLines(final Graphics2D g2d) {

		int angle;
		int distance;
		int angleCorrUp;
		int limitInf, limitMax;

		limitInf = pitchAngle / 10 - 5;
		if (limitInf < -18) {
			limitInf = -18;
		}
		limitMax = limitInf + 11;
		if (limitMax > 18) {
			limitMax = 19;
		}

		for (int i = limitInf; i < limitMax; i++) {

			angle = i * 10; // Display the text at the right "height"
			angleCorrUp = angle - pitchAngle;
			distance = Math.abs(i * 5); // Put the text and the lines length at
										// the right position

			g2d.setPaint(Color.white);
			g2d.setStroke(new BasicStroke(2));
			g2d.setFont(writing);

			// Longer markers
			markerLine = new Line2D.Float((float) (centerPoint.getX()
					- dimMarker10Deg - distance),
					(float) (centerPoint.getY() - radius
							* Math.sin(Math.toRadians(angleCorrUp))),
					(float) (centerPoint.getX() + dimMarker10Deg + distance),
					(float) (centerPoint.getY() - radius
							* Math.sin(Math.toRadians(angleCorrUp))));

			g2d.draw(markerLine);

			// Short markers
			markerLine = new Line2D.Float(
					(float) (centerPoint.getX() - dimMarker5Deg),
					(float) (centerPoint.getY() - radius
							* Math.sin(Math.toRadians(angleCorrUp + 5))),
					(float) (centerPoint.getX() + dimMarker5Deg),
					(float) (centerPoint.getY() - radius
							* Math.sin(Math.toRadians(angleCorrUp + 5))));

			g2d.draw(markerLine);

			// Writing routine
			g2d.drawString(
					Messages.getString("Sonderzeichen.Space") + angle, //$NON-NLS-1$
					(float) (centerPoint.getX() - dimMarker10Deg - distance - 25),
					(float) (centerPoint.getY() - (radius
							* Math.sin(Math.toRadians(angleCorrUp)) - 5)));
			g2d.drawString(
					Messages.getString("Sonderzeichen.Space") + angle, //$NON-NLS-1$
					(float) (centerPoint.getX() + dimMarker10Deg + distance + 8),
					(float) (centerPoint.getY() - (radius
							* Math.sin(Math.toRadians(angleCorrUp)) - 5)));

		}

		// Draw the center shape
		centerShape = new GeneralPath(Path2D.WIND_EVEN_ODD);
		centerShape.moveTo(centerPoint.getX() - radius / 2.5,
				centerPoint.getY());
		centerShape.lineTo(centerPoint.getX() - 25, centerPoint.getY());
		centerShape.moveTo(centerPoint.getX() - 40, centerPoint.getY());
		centerShape.lineTo(centerPoint.getX() - 20, centerPoint.getY() + 20);
		centerShape.lineTo(centerPoint.getX(), centerPoint.getY());
		centerShape.lineTo(centerPoint.getX() + 20, centerPoint.getY() + 20);
		centerShape.lineTo(centerPoint.getX() + 40, centerPoint.getY());
		centerShape.moveTo(centerPoint.getX() + radius / 2.5,
				centerPoint.getY());
		centerShape.lineTo(centerPoint.getX() + 25, centerPoint.getY());

		g2d.setPaint(Color.white);
		g2d.setStroke(new BasicStroke(3));
		g2d.draw(centerShape);
	}

	private void drawMarkers(final Graphics2D g2d) {

		// Draw the lines on the Horizon
		drawLines(g2d);

		// Draw the Bank roll display on the top
		drawBankRollTriangle(g2d);

	}

	/****************************
	 * Main paintComponent method
	 ***************************/
	@Override
	public void paintComponent(final Graphics g) {

		/*************************************************
		 * According to the info stored in definePanel the filtered or pure
		 * values are picked up from the static variables defined in the main
		 * class
		 ************************************************/

		pitchAngle = (int) ArduCopterGUI.pitchValue;
		rollAngle = (int) ArduCopterGUI.rollValue;
		yawAngle = (int) ArduCopterGUI.yawValue;

		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		drawHorizon(g2d);

		g2d.setStroke(new BasicStroke(2));
		g2d.setPaint(Color.white);

		// Draw the Bank roll lines on the top
		drawBankRollMarker(g2d);

		drawBCardinaldirectionlable(g2d);

		// Draw the Bank roll display on the top
		drawCardinaldirection(g2d);

	}
}
