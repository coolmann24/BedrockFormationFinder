
public class NetherChunkBedrockData extends ChunkBedrockData
{
	private int[][][] roofbedrockdata;
	
	public NetherChunkBedrockData(int[][][] bedrockdata, int xchunk, int zchunk, int[][][] roofbedrockdata)
	{
		super(bedrockdata, xchunk, zchunk);
		this.roofbedrockdata = roofbedrockdata;
	}
	
	public int getChunkBlockId(int x, int y, int z)
	{
		if(y >= 123 && !(y > 127))
		{
			return roofbedrockdata[x][y - 123][z];
		}
		else
		{
			return super.getChunkBlockId(x, y, z);
		}
	}
	
	public void setChunkBlockId(int x, int y, int z, int id)
	{
		if(y >= 123 && y <= 127)
		{
			roofbedrockdata[x][y - 123][z] = id;
			return;
		}
		super.setChunkBlockId(x, y, z, id);
	}
}
