package com.ed.testmod.utils;

import java.util.Random;

import net.minecraft.util.math.Vec3d;
public class ModMathHelper {
	
	
	public static Vec3d MakePlaneVector(Vec3d v) {
		
		return new Vec3d(v.x,0,v.z);
	}
	
	public static class Point{
		public double x;
		public double y;
		public double z;
		public static Point ZERO = new Point(0,0,0);
		public Point(double X,double Y,double Z) {
			this.x = X;
			this.y = Y;
			this.z = Z;
		}
		public double distSq(Point A,Point B) {
			double deltaX = A.x - B.x;
			double deltaY = A.y - B.y;
		    double deltaZ = A.z - B.z;
		    return deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
		}
		public Point(Vec3d r) {
			this.x = r.x;
			this.y = r.y;
			this.z = r.z;
		}
		
		public static Vec3d getVec(Point A,Point B) {
			return new Vec3d(B.x - A.x,B.y - A.y,B.z - A.z);
		}
	}
	public static class Circle {
		public double CenterX;
		public double CenterY;
		public double CenterZ;
		public double Radius;
		 public Circle(double cx,double cy,double cz,double r) {
			 this.CenterX = cx;
			 this.CenterY = cy;
			 this.CenterZ = cz;
			 this.Radius = r;
		 }
		 public Circle(Point P,double r) {
			 this.CenterX = P.x;
			 this.CenterY = P.y;
			 this.CenterZ = P.z;
			 this.Radius = r;
		 }
		  public boolean isInCircle(Point p) {
			  double sumSq = (p.x - this.CenterX) * (p.x - this.CenterX) + (p.z - this.CenterZ)*(p.z - this.CenterZ); 
			  double rSq = this.Radius * this.Radius;
			  if(Math.abs(sumSq - rSq) < 1.0E-4D || rSq - sumSq > 1.0E-4D) {
				  return true;
			  } else {
				  return false;
			  }
			  
		  }
		  public boolean isInCircle(double x,double y,double z) {
			  Point p = new Point(x,y,z);
			  return isInCircle(p);
		  }
		  public Point getCenter() {
			  Point C = new Point(this.CenterX,this.CenterY,this.CenterZ);
			  return C;
		  }
		  public Point getRandomPointOfCircle(Random rand){
			  double fi = rand.nextDouble() *  2 * Math.PI;
			  double offset = rand.nextDouble();
			  double X = this.CenterX + Math.cos(fi) * this.Radius * offset;
			  double Z = this.CenterZ + Math.sin(fi) * this.Radius * offset;
			  return new Point(X,this.CenterY,Z);
		  }
		  public Point getRandomPointOfCircle(Random rand,double r) {
			  double fi = rand.nextDouble() * 2 * Math.PI;
			  double X = this.CenterX + Math.cos(fi) * r;
			  double Z = this.CenterZ + Math.sin(fi) * r;
			  return new Point(X,this.CenterY,Z);
		  }
		  public Point getRandomPointOfCircleBoard(Random rand) {
			  double fi = rand.nextDouble() * 2 * Math.PI;
			  double X = this.CenterX + Math.cos(fi) * this.Radius;
			  double Z = this.CenterZ + Math.sin(fi) * this.Radius;
			  return new Point(X,this.CenterY,Z);
		  }
		  public Point getPointInCircleWithDev(int n,int i) {
			  double fi = 2 * (Math.PI / n) * i;
			  double X = this.CenterX + Math.cos(fi) * this.Radius;
			  double Z = this.CenterZ + Math.sin(fi) * this.Radius;
			  return new Point(X,this.CenterY,Z);
		  }
		  
	}
	public static class Line{
		public Vec3d direction;
		public Point P;
		public Line() {
			direction = Vec3d.ZERO;
			P = Point.ZERO;
		}
		public Line(Point A,Vec3d v) {
			P.x = A.x;
			P.y = A.y;
			P.z = A.z;
			direction = v;
		}
		
		public Line(Point A,Point B) {
			direction = Point.getVec(A, B);
			P = A;
		}
		
		public Vec3d getDirection() {
			return direction;
		}
		
		public Point getFixPoint() {
			return P;
		}
		
		public void resetFixPoint(Point Q) {
			P = Q;
		}
		
		public void resetDirection(Vec3d v) {
			direction = v;
		}
		
		public Point findPointByY(double y) {
			if(!(Math.abs(direction.y) < 1.0E-4D)) {
			double t = (y - P.y) / direction.y;
			return new Point(P.x + direction.x * t,y,P.z + direction.z * t);
			} else {
				return P;
			}
		}
		
		public Point findPointByDist(double signDist) {
			Vec3d dir = direction.normalize().scale(signDist);
			return new Point(P.x + dir.x,P.y + dir.y,P.z + dir.z);
		}
		
		public Point CanFindByY(double y,double signDist) {
			Point F = findPointByY(y);
			if(P == F && !(Math.abs(P.y - y) < 1.0E-4D)) {
				return findPointByDist(signDist);

			} else {
			return F;
			}
		}
	}
}
