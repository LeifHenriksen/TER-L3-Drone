package com.dji.GSDemo.GoogleMap;

public class Line{

    Point p1;
    Point p2;

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }


    public boolean onSegment(Point p3)
    {
        if (p3.x <= Math.max(this.getP1().x, this.getP2().x) && p3.x >= Math.min(this.getP1().x, this.getP2().x) &&
                p3.y <= Math.max(this.getP1().y, this.getP2().y) && p3.y >= Math.min(this.getP1().y, this.getP2().y))
            return true;

        return false;
    }

    public Orientation getOrientation(Point p3)
    {
        int val = (int)((this.getP2().getY()  - this.getP1().getY()) * (p3.getX() - this.getP2().getX()) -
                (this.getP2().getX() - this.getP1().getX()) * (p3.getY() - this.getP2().getY()));

        if (val == 0)
            return Orientation.Collinear;

        return (val > 0)? Orientation.Clockwise: Orientation.CounterClockwise;
    }

    public double distancePointToPointOfIntersection(Point p, Line l2)
    {
        Point pointInter = this.lineLineIntersection(l2);
        if(pointInter == null)
        {
            return -1;
        }
        else
        {
            return p.distance(pointInter);
        }

    }

    //Point of intersection
    public Point lineLineIntersection(Line l2)
    {
        // Line AB represented as a1x + b1y = c1
        double a1 = this.getP2().getY() - this.getP1().getY();
        double b1 = this.getP1().getX() - this.getP2().getX();
        double c1 = a1*(this.getP1().getX()) + b1*(this.getP1().getY());

        // Line CD represented as a2x + b2y = c2
        double a2 = l2.getP2().getY() - l2.getP1().getY();
        double b2 = l2.getP1().getX() - l2.getP2().getX();
        double c2 = a2*(l2.getP1().getX()) + b2*(l2.getP1().getY());

        double determinant = a1*b2 - a2*b1;

        if (determinant == 0)
        {
            // The lines are parallel.
            return null;
        }
        else
        {
            double x = (b2*c1 - b1*c2)/determinant;
            double y = (a1*c2 - a2*c1)/determinant;
            return new Point(x, y,-1,-1,-1);
        }
    }

    public boolean pointInsideLine(Point p) {
        return this.getP1().distance(this.getP2()) == this.getP1().distance(p) + p.distance(this.getP2());
    }

    public boolean intersect(Line l2)
    {
        Orientation o1 = this.getOrientation(l2.getP1());
        Orientation o2 = this.getOrientation(l2.getP2());
        Orientation o3 = l2.getOrientation(this.getP1());
        Orientation o4 = l2.getOrientation(this.getP2());

        if(!o1.equals(o2) && !o3.equals(o4))
            return true;


        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (o1.equals(Orientation.Collinear) && this.onSegment(l2.getP1()))
            return true;

        // p1, q1 and q2 are colinear and q2 lies on segment p1q1
        if (o2.equals(Orientation.Collinear) && this.onSegment(l2.getP2()))
            return true;

        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3.equals(Orientation.Collinear) && l2.onSegment(this.getP1()))
            return true;

        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4.equals(Orientation.Collinear) && l2.onSegment(this.getP2()))
            return true;


        return false;
    }

    @Override
    public String toString() {
        return "Line [p1=" + p1 + ", p2=" + p2 + "]";
    }
}