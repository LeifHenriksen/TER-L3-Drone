package com.dji.GSDemo.GoogleMap;


public class Point{
    double x;
    double y;
    double z;
    double lat;
    double lng;
    public static final double RADIUS = 6378137.0; 		//rayon de la terre en metres

    public Point(double x, double y, double z, double lat, double lng) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.lat = lat;
        this.lng = lng;
    }
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.lat = -1;
        this.lng = -1;
    }
    public Point(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
        this.x = this.LngToX();
        this.y = this.LatToY();
        this.z = -1;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getZ() {
        return z;
    }
    public void setZ(double z) {
        this.z = z;
    }
    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }
    /*
    static public Point maxX(ArrayList<Point> listeP)
    {
        Point max = listeP.get(0);
        for(int i = 1; i<listeP.size(); i++)
            if(max.getX()<listeP.get(i).getX())
                max = listeP.get(i);

        return max;
    }
    static public Point maxY(ArrayList<Point> listeP)
    {
        Point max = listeP.get(0);
        for(int i = 1; i<listeP.size(); i++)
            if(max.getY()<listeP.get(i).getY())
                max = listeP.get(i);

        return max;
    }
    */
    public double distance(Point p2){
        return Math.sqrt(((p2.getX() - this.getX())*(p2.getX() - this.getX()))+
                ((p2.getY() - this.getY())*(p2.getY() - this.getY())));
    }

    public double decalageMercatorLat(double m_lat)
    {

        double decalLat = m_lat/RADIUS;

        return this.getLat() + (decalLat * (180/Math.PI));
    }

    public double decalageMercatorLng(double m_lng)
    {
        double decalLng = m_lng/(RADIUS*Math.cos((Math.PI*this.getLat())/180));

        return this.getLng() + (decalLng * (180/Math.PI));
    }

    public void decalageLng(double metres)
    {
        this.lng = this.decalageMercatorLng(metres);
    }

    public void decalageLat(double metres)
    {
        this.lat = this.decalageMercatorLat(metres);
    }

    public void decalage(double m_lat, double m_lng)
    {
        this.decalageLng(m_lng);
        this.decalageLat(m_lat);
        this.x = this.LngToX();
        this.y = this.LatToY();
    }
    private double LatToY()
    {
        return Math.log(Math.tan(Math.PI / 4 + Math.toRadians(this.lat) / 2)) * RADIUS;
    }
    private double LngToX()
    {
        return Math.toRadians(this.lng) * RADIUS;
    }
    @Override
    public String toString() {
        return "Point [x=" + x + ", y=" + y + ", z=" + z + ", lat=" + lat + ", lng=" + lng + "]";
    }

}