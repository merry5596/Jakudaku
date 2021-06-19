package passionx3.jkdk.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

@Component
public class WaterMarkUtil {

	public void setWaterMarkFile(String alias, String orgFileName) {

		String strWText = alias; // 워터마크 글씨(판매자 alias)

		String uploadPath = Paths.get("C:", "jkdk", "upload", FileUtils.today).toString();

		// create graphics context and enable anti-aliasing

		try {
			File sourceImageFile = new File(uploadPath, orgFileName); // 원래 썸네일
			String fileName = "water_" + orgFileName;
			File destImageFile = new File(uploadPath, fileName); // 바뀐 썸네일
			BufferedImage sourceImage = ImageIO.read(sourceImageFile);
			Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

			// Graphics2D g2d = original.createGraphics();

			g2d.scale(1, 1);
			g2d.addRenderingHints(
					new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			// create watermark text shape for rendering
			Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 180);
			GlyphVector fontGV = font.createGlyphVector(g2d.getFontRenderContext(), strWText);
			Rectangle size = fontGV.getPixelBounds(g2d.getFontRenderContext(), 0, 0);

			Shape textShape = fontGV.getOutline();
			// double textWidth = size.getWidth();
			double textWidth = size.getWidth();

			// double textHeight = size.getHeight();
			double textHeight = size.getHeight() * 2; // 텍스트 간격이다.
			// AffineTransform rotate45 = AffineTransform.getRotateInstance(Math.PI / 4d);
			AffineTransform rotate45 = AffineTransform.getRotateInstance(Math.PI / 5d);
			Shape rotatedText = rotate45.createTransformedShape(textShape);

			// use a gradient that repeats 4 times
			g2d.setPaint(new GradientPaint(0, 0, new Color(0.3f, 0.3f, 0.3f, 0.3f), sourceImage.getWidth() / 2,
					sourceImage.getHeight() / 2, new Color(0.3f, 0.3f, 0.3f, 0.3f)));
			// new Color(1f, 1f, 1f, 0.1f)));
			// g2d.setStroke(new BasicStroke(0.5f));
			g2d.setStroke(new BasicStroke(1f));

			// step in y direction is calc'ed using pythagoras + 5 pixel padding
			// double yStep = Math.sqrt(textWidth * textWidth / 2) + 2;
			double yStep = Math.sqrt(textWidth * textWidth / 2) + 500; //

			// step over image rendering watermark text
			// for (double x = -textHeight * 3; x < sourceImage.getWidth(); x += (textHeight
			// * 3)) {

			for (double x = -textHeight; x < sourceImage.getWidth() / 2; x += textHeight) {
				double y = -yStep;

				for (; y < sourceImage.getHeight(); y += yStep) {
					g2d.draw(rotatedText);
					g2d.fill(rotatedText);
					g2d.translate(0, yStep);
				}

				g2d.translate(textHeight * 3, -(y + yStep));
			}

			ImageIO.write(sourceImage, "jpg", destImageFile);
			g2d.dispose();

			System.out.println("The tex watermark is added to the image.");

		} catch (IOException ex) {
			System.err.println(ex);
		}

	}

}