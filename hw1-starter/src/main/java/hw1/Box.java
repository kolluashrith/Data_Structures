package hw1;

import java.awt.Color;

/**
 * A Box is a square Sprite with a fixed length.
 */
public class Box extends Sprite {

  /**
   * Construct a Box.
   *
   * @param x coordinate of the top left corner.
   * @param y coordinate of the top left corner.
   * @param color the color of this Box.
   */
  public Box(double x, double y, Color color) {
    super(x, y, GameConstant.BOX_LENGTH, GameConstant.BOX_LENGTH, color);
  }

  @Override
  public void draw() {
    StdDraw.setPenColor(getColor());
    double halfLength = GameConstant.BOX_LENGTH / 2.0;
    double centerX = getX() + halfLength;
    double centerY = getY() - halfLength;
    StdDraw.filledSquare(centerX, centerY, halfLength);
  }

  /**
   * Check if this Box intersects with another Box.
   *
   * @param other the other Box.
   * @return true if this Box intersects with the other Box.
   */
  public boolean intersects(Box other) {
    double x = super.getX();
    double x2 = x + super.getWidth();
    double y = super.getY();
    double y2 = y - super.getHeight();
    double ox = other.getX();
    double ox2 = ox + other.getWidth();
    double oy = other.getY();
    double oy2 = oy - other.getHeight();

    return (((x >= ox && x <= ox2) || (x2 >= ox && x2 <= ox2)) && ((y <= oy && y >= oy2) || (y2 <= oy && y2 >= oy2)));
  }
}