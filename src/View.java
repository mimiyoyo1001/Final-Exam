import javax.swing.*;
import java.awt.*;


public class View
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() -> displayJFrame());
	}

	static void displayJFrame()
	{
		Thread thread = new Thread();
		JFrame frame = new JFrame("我的視窗");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(400, 200);
		frame.setVisible(true);

		Container contentPane = frame.getContentPane();
		BorderLayout b = (BorderLayout) contentPane.getLayout();
		b.setHgap(10);
		TextField text1 = new TextField("");
		frame.add(text1, BorderLayout.PAGE_START);

		JButton ss = new JButton("送出");
		frame.add(ss, BorderLayout.CENTER);
		ss.addActionListener(e ->
				text1.setText("1213")
		);

		JButton ll = new JButton("連線");
		frame.add(ll, BorderLayout.LINE_START);
		ll.addActionListener(e ->
		{
			client.runClient();
		});

		JButton dd = new JButton("中斷");
		frame.add(dd, BorderLayout.LINE_END);

		TextField text2 = new TextField("");
		frame.add(text2, BorderLayout.PAGE_END);
		thread.start();
	}
}

