
public class ChunkBedrockData
{
	private int[][][] bedrockdata;
	private int xchunk, zchunk;
	
	public ChunkBedrockData(int[][][] bedrockdata, int xchunk, int zchunk)
	{
		this.bedrockdata = bedrockdata;
		this.xchunk = xchunk;
		this.zchunk = zchunk;
	}
	
	public int getChunkBlockId(int x, int y, int z)
	{
		if(y < 0 || (y > 4 && y < 123) || y > 127) return 0;
		
		return bedrockdata[x][y][z];
	}
	
	public void setChunkBlockId(int x, int y, int z, int id)
	{
		if(y < 0 || y > 4) return;
		
		bedrockdata[x][y][z] = id;
	}
	
	public int getChunkX()
	{
		return xchunk;
	}
	
	public int getChunkZ()
	{
		return zchunk;
	}
	
}
