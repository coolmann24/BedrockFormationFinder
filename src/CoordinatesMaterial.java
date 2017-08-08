
public class CoordinatesMaterial 
{
	private int x, y, z, id;
	
	public CoordinatesMaterial(int x, int y, int z, int id)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = id;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getZ()
	{
		return z;
	}
	
	public int getID()// 0 = air, 1 = bedrock
	{
		return id;
	}
}
