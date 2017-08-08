import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Bffgui extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField xmin, xmax, zmin, zmax, ybase, xrelativeadd, yrelativeadd, zrelativeadd, typerelativeadd;
	private JTextField xmindummy, xmaxdummy, zmindummy, zmaxdummy, ybasedummy, xrelativeadddummy, yrelativeadddummy, zrelativeadddummy, typerelativeadddummy, yinfo;
	private JButton searchtoggle, addrelativeloc, removerelativeloc, showrelativelocs, worldtoggle;
	private JPanel panel, coords, relativelocs, outputarea;
	private JTextArea output;
	private JScrollPane scroll;
	
	private ArrayList<CoordinatesMaterial> coordsmaterials;
	
	public Bffgui()
	{
		coordsmaterials = new ArrayList<CoordinatesMaterial>();
		
		this.setTitle("Bedrock Formation Finder");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(750, 400);
		
		
		panel = new JPanel();
		panel.setSize(750, 500);
		this.setContentPane(panel);
		
		coords = new JPanel();
		coords.setSize(200, 500);
		coords.setLayout(new BoxLayout(coords, BoxLayout.PAGE_AXIS));
		panel.add(coords, BorderLayout.WEST);
		
		relativelocs = new JPanel();
		relativelocs.setSize(200, 500);
		relativelocs.setLayout(new BoxLayout(relativelocs, BoxLayout.PAGE_AXIS));
		panel.add(relativelocs, BorderLayout.CENTER);
		
		outputarea = new JPanel();
		outputarea.setPreferredSize(new Dimension(275, 300));
		outputarea.setLayout(new BoxLayout(outputarea, BoxLayout.PAGE_AXIS));
		panel.add(outputarea, BorderLayout.CENTER);
		
		searchtoggle = new JButton("Begin Search");
		addrelativeloc = new JButton("Add Coords");
		removerelativeloc = new JButton("Remove Coords");
		worldtoggle = new JButton("Overworld");
		showrelativelocs = new JButton("Show List");
		
		
		
		xmin = new JTextField("-1000");
		xmindummy = new JTextField("Minimum X Coordinate");
		xmindummy.setEditable(false);
		xmin.setLocation(375, 250);
		coords.add(xmindummy);
		coords.add(xmin);
		
		
		zmin = new JTextField("-1000");
		zmindummy = new JTextField("Minimum Z Coordinate");
		zmindummy.setEditable(false);
		coords.add(zmindummy);
		coords.add(zmin);
		
		xmax = new JTextField("1000");
		xmaxdummy = new JTextField("Maximum X Coordinate");
		xmaxdummy.setEditable(false);
		coords.add(xmaxdummy);
		coords.add(xmax);
		
		zmax = new JTextField("1000");
		zmaxdummy = new JTextField("Maximum Z Coordinate");
		zmaxdummy.setEditable(false);
		coords.add(zmaxdummy);
		coords.add(zmax);
		
		ybase = new JTextField("4");
		ybasedummy = new JTextField("Formation Y Base Value");
		ybasedummy.setEditable(false);
		coords.add(ybasedummy);
		coords.add(ybase);
		
		yinfo = new JTextField("Nether roof: 123-127, Floor: 0-4");
		yinfo.setEditable(false);
		coords.add(yinfo);
		
		coords.add(worldtoggle);
		
		xrelativeadd = new JTextField("0");
		xrelativeadddummy = new JTextField("Formation Relative X Value");
		xrelativeadddummy.setEditable(false);
		relativelocs.add(xrelativeadddummy);
		relativelocs.add(xrelativeadd);
		
		yrelativeadd = new JTextField("0");
		yrelativeadddummy = new JTextField("Formation Relative Y Value");
		yrelativeadddummy.setEditable(false);
		relativelocs.add(yrelativeadddummy);
		relativelocs.add(yrelativeadd);
		
		zrelativeadd = new JTextField("0");
		zrelativeadddummy = new JTextField("Formation Relative Z Value");
		zrelativeadddummy.setEditable(false);
		relativelocs.add(zrelativeadddummy);
		relativelocs.add(zrelativeadd);
		
		typerelativeadd = new JTextField("1");
		typerelativeadddummy = new JTextField("Formation Type: 1 = Bedrock, 0 = Not Bedrock");
		typerelativeadddummy.setEditable(false);
		relativelocs.add(typerelativeadddummy);
		relativelocs.add(typerelativeadd);
		
		relativelocs.add(addrelativeloc);
		relativelocs.add(removerelativeloc);
		relativelocs.add(showrelativelocs);
		
		output = new JTextArea();
		scroll = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		output.setEditable(false);
		outputarea.add(scroll, BorderLayout.CENTER);
		outputarea.add(searchtoggle);
		
		worldtoggle.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				if(!searchtoggle.getText().equals("Begin Search")) return;
				
				if(event.getSource() == worldtoggle)
				{
					if(worldtoggle.getText().equals("Overworld"))
					{
						worldtoggle.setText("Nether");
						
					}
					else
					{
						worldtoggle.setText("Overworld");
					}
				}
			}
			
		});
		
		addrelativeloc.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				if(!searchtoggle.getText().equals("Begin Search")) return;
				
				
				if(event.getSource() == addrelativeloc)
				{
					int x, y, z, id;
					
					try
					{
						x = Integer.parseInt(xrelativeadd.getText());
						y = Integer.parseInt(yrelativeadd.getText());
						z = Integer.parseInt(zrelativeadd.getText());
						id = Integer.parseInt(typerelativeadd.getText());
					}
					catch(Exception e)
					{
						output.append("Invalid input, all inputs must be integers >= zero!\n");
						return;
					}
					if(x < 0 || y < 0 || z < 0)
					{
						output.append("Invalid input, all inputs must be integers >= zero!\n");
						return;
					}
					if(id < 0 || id > 1)
					{
						output.append("Invalid input, the block type must be 1 or 0!\n");
						return;
					}
					for(CoordinatesMaterial c : coordsmaterials)
					{
						if(c.getX() == x && c.getY() == y && c.getZ() == z)
						{
							output.append("Invalid input, this input is already stored!\n");
							return;
						}
					}
					coordsmaterials.add(new CoordinatesMaterial(x, y, z, id));
					output.append("Input has been stored: (" + x + ", " + y + ", " + z + ") with id of " + id + "\n");
					return;
				}
			}
		});
		
		removerelativeloc.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				if(!searchtoggle.getText().equals("Begin Search")) return;
				
				if(event.getSource() == removerelativeloc)
				{
					int x, y, z;
					try
					{
						x = Integer.parseInt(xrelativeadd.getText());
						y = Integer.parseInt(yrelativeadd.getText());
						z = Integer.parseInt(zrelativeadd.getText());
					}
					catch(Exception e)
					{
						output.append("Invalid input, all inputs must be integers >= zero!\n");
						return;
					}
					for(int i = coordsmaterials.size() - 1; i >= 0; i--)
					{
						CoordinatesMaterial c = coordsmaterials.get(i);
						if(c.getX() == x && c.getY() == y && c.getZ() == z)
						{
							coordsmaterials.remove(i);
							output.append("Input removed!\n");
							return;
						}
					}
					output.append("Input not found!\n");
					return;
				}
			}
		});
		
		showrelativelocs.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				if(!searchtoggle.getText().equals("Begin Search")) return;
				
				if(event.getSource() == showrelativelocs)
				{
					for(CoordinatesMaterial c : coordsmaterials)
					{
						output.append("(" + c.getX() + ", " + c.getY() + ", " + c.getZ() + ") id = " + c.getID() + "\n");
					}
				}
			}
		});
		
		searchtoggle.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				if(event.getSource() == searchtoggle)
				{
					if(searchtoggle.getText().equals("Begin Search"))
					{
						int xminc, xmaxc, zminc, zmaxc, ybasev, worldid;
						
						try
						{
							xminc = (Integer.parseInt(xmin.getText()))/16;
							xmaxc = (Integer.parseInt(xmax.getText()))/16;
							zminc = (Integer.parseInt(zmin.getText()))/16;
							zmaxc = (Integer.parseInt(zmax.getText()))/16;
							ybasev = Integer.parseInt(ybase.getText());
						}
						catch(Exception e)
						{
							output.append("Inputs must all be integer values! Search cancelled!\n");
							return;
						}
						String s = worldtoggle.getText();
						if(s.equals("Overworld"))
						{
							worldid = 1;
						}
						else
						{
							worldid = 0;
						}
						
						Runnable task = new Runnable()
						{
							@Override
							public void run()
							{
								try
								{
									Main.main(coordsmaterials, xminc, xmaxc, zminc, zmaxc, ybasev, worldid, searchtoggle, output);
								}	
								catch(Exception e)
								{}
							}
						};
						new Thread(task, "Service Thread").start();;
						
						searchtoggle.setText("Stop Search");
						return;
					}
					else if(searchtoggle.getText().equals("Stop Search"))
					{
						searchtoggle.setText("Stopping");
					}
				}
			}
		});
		
		pack();
		this.setVisible(true);
		
	}
	
	public static void main(String[] args)
	{
		new Bffgui();
	}
	
}
