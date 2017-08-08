import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class Main
{
	public static void main(ArrayList<CoordinatesMaterial> coordsmaterials, int minchunkx, int maxchunkx, int minchunkz, int maxchunkz, int ybase, int world, JButton searchbutton, JTextArea textarea)
	{
		
		
		int xmaxdistancerelative = -1;
		int zmaxdistancerelative = -1;
		
		for(CoordinatesMaterial m : coordsmaterials)
		{
			if(m.getX() > xmaxdistancerelative) xmaxdistancerelative = m.getX();
			
			if(m.getZ() > zmaxdistancerelative) zmaxdistancerelative = m.getZ();
		}
		
		int xchunksout = (int)Math.ceil(((double)(xmaxdistancerelative)/16.0));
		int zchunksout = (int)Math.ceil(((double)(zmaxdistancerelative)/16.0));
		
		
		if(minchunkx > maxchunkx)
		{
			int n = minchunkx;
			minchunkx = maxchunkx;
			maxchunkx = n;
		}
		
		if(minchunkz > maxchunkz)
		{
			int n = minchunkz;
			minchunkz = maxchunkz;
			maxchunkz = n;
		}
		
		Random rand = new Random();
		
		ArrayList<ChunkBedrockData> currentchunksbedrockdata = new ArrayList<ChunkBedrockData>();
		
		for(int i = minchunkx; i <= maxchunkx; i++)
		{
			for(int j = minchunkz; j <=maxchunkz; j++)
			{
				//rand.setSeed((long)i * 341873128712L + (long)j * 132897987541L);
				
				if(searchbutton.getText().equals("Stopping"))
				{
					searchbutton.setText("Begin Search");
					textarea.append("Search aborted.\n");
					return;
				}
				
				for(int p = 0; p <= xchunksout; p++)
				{
					for(int q = 0; q <= zchunksout; q++)
					{
						boolean haschunk = false;
						for(ChunkBedrockData c : currentchunksbedrockdata)
						{
							if(c.getChunkX() == p + i && c.getChunkZ() == j + q)
							{
								haschunk = true;
							}
						}
						if(!haschunk)
						{
							rand.setSeed((long)(i + p) * 341873128712L + (long)(j + q) * 132897987541L);
							
							//Arrays.fill(chunkdata, 0);
							
							ChunkBedrockData c;
							
							if(world == 0) c = new NetherChunkBedrockData(new int[16][5][16], i + p, j + q, new int[16][5][16]);
							else c = new ChunkBedrockData(new int[16][5][16], i + p, j + q);
							
							for(int i1 = 0; i1 < 16; i1++)
							{
								for(int j1 = 0; j1 < 16; j1++)
								{
									rand.nextDouble();
									
									if(world == 0)
									{
										rand.nextDouble();
										rand.nextDouble();
									}
									
									int ymaxstart;
									if(world == 0) ymaxstart = 127;
									else ymaxstart = 255;
									
									for (int k1 = ymaxstart; k1 >= 0; --k1)
							        {
							            if (world == 1 && k1 <= rand.nextInt(5))
							            {
							                //list.add(new Coordinates(i*16 + j1, k1, j*16 + i1));
							            	c.setChunkBlockId(j1, k1, i1, 1);
							            }
							            if(world == 0)
							            {
							            	if(k1 < 127 - rand.nextInt(5) && k1 > rand.nextInt(5))
							            	{}
							            	else
							            	{
							            		c.setChunkBlockId(j1, k1, i1, 1);
							            	}
							            }
							        }
								}
							}
							
							currentchunksbedrockdata.add(c);
						}
						
						for(int n = currentchunksbedrockdata.size() - 1; n >= 0; n--)
						{
							ChunkBedrockData c = currentchunksbedrockdata.get(n);
							
							if(c.getChunkX() < i || c.getChunkX() > i + xchunksout || c.getChunkZ() < j || c.getChunkZ() > j + zchunksout)
							{
								currentchunksbedrockdata.remove(c);
							}
						}
						
					}
				}
				
				for(int f = 0; f < 16; f++)
				{
					for(int g = 0; g < 16; g++)
					{
						boolean formationfound = true;
						
						for(CoordinatesMaterial m : coordsmaterials)
						{
							int xrchunk = (m.getX() + f)/16;
							int zrchunk = (m.getZ() + g)/16;
							
							for(ChunkBedrockData c : currentchunksbedrockdata)
							{
								if(c.getChunkX() == i + xrchunk && c.getChunkZ() == j + zrchunk)
								{
									if(m.getID() != c.getChunkBlockId((m.getX() + f)%16, ybase + m.getY(), (m.getZ() + g)%16))
									{
										formationfound = false;
									}
								}
							}
							if(!formationfound) break;
						}
						
						if(formationfound)
						{
							//System.out.println("X: " + (i*16 + f) + "  Z: " + (j*16 + g));
							textarea.append("X: " + (i*16 + f) + "  Z: " + (j*16 + g) + "\n");
						}
					}
				}
				
				
			}
		}
		textarea.append("Search completed.\n");
		searchbutton.setText("Begin Search");
	}
}
