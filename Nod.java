import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Nod extends JFrame{

	JButton ange, visa;
	HashMap <String, Position> mapMap = new HashMap<>();
	DefaultListModel <String> listData = new DefaultListModel<>();
	JList <String> nameList = new JList<>(listData);
	Map map = new Map();
	Position pos;

	public Nod(){

		super("Nod");

		JPanel vänster = new JPanel();
		vänster.setLayout(new BoxLayout(vänster, BoxLayout.Y_AXIS));
		add(vänster, BorderLayout.WEST);
		nameList.setVisibleRowCount(10);
		nameList.setFixedCellWidth(80);
		vänster.add(new JScrollPane(nameList));

		add(map, BorderLayout.CENTER);

		JPanel south = new JPanel();
		add(south, BorderLayout.SOUTH);
		ange = new JButton("Ange");
		visa = new JButton("Visa");
		south.add(ange);
		south.add(visa);
		visa.addActionListener(new KnappLyssTwo());
		ange.addActionListener(new KnappLyssOne());

		setVisible(true);
		setSize(900, 622);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String []args){
		new Nod();
	}

	public class Map extends JPanel{

		private ImageIcon pic = new ImageIcon("NodPlan3.png");
		private int x, y;
		private boolean show;

		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(pic.getImage(),0,0, this);
			if (show){
				g.setColor(Color.RED);
				g.fillOval(x-10,y-10,15,15);
			}
		}

		public void setXY(int x, int y){
			this.x = x;
			this.y = y;
		}

		public void setShow(boolean s){
			show = s;
			repaint();

		}
	}

	public class KnappLyssOne implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {

			String name = JOptionPane.showInputDialog(null, "Namn:");
			listData.addElement(name);
			boolean show = true;

			map.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					if(e.getButton() == MouseEvent.BUTTON1){
						int x = e.getX();
						int y = e.getY();
						pos = new Position(x,y);
						map.setXY(x, y);
						map.setShow(show);
						mapMap.put(name, pos);
						map.removeMouseListener(this);
					}
				}
			});

		}
		
	}

	public class KnappLyssTwo implements ActionListener{

		public void actionPerformed(ActionEvent arg0){

			String name = nameList.getSelectedValue();
			boolean show = true;

			if(mapMap.containsKey(name)){
				pos = mapMap.get(name);
				map.setXY(pos.getX(), pos.getY());
				map.setShow(show);
			}else{
				JOptionPane.showMessageDialog(null, "Something went wrong!");
			}
		}
	}

	public class Position {

		private int x;
		private int y;

		public Position (int x, int y){
			this.x = x;
			this.y = y;
		}

		public int getX(){
			return x;
		}

		public int getY(){
			return y;
		}

	}
}
