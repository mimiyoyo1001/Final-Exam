import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class View
{
	private static JButton disconnectBtn;
	private static JButton connectBtn;
	private static JButton sentBtn;
	private static TextField sendTextField;
	private static TextField getMessageTextField;

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() -> displayJFrame());
	}

	static void displayJFrame()
	{
		Thread thread = new Thread();
		JFrame frame = new JFrame("我的視窗");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				disconnectBtnOnClick();
			}
		});

		frame.setSize(400, 200);
		frame.setVisible(true);
		frame.setResizable(false);

		Container contentPane = frame.getContentPane();
		BorderLayout b = (BorderLayout) contentPane.getLayout();
		b.setHgap(10);
		sendTextField = new TextField("");
		frame.add(sendTextField, BorderLayout.PAGE_START);
		sendTextField.setEnabled(false);

		sentBtn = new JButton("送出");
		frame.add(sentBtn, BorderLayout.CENTER);
		sentBtn.setEnabled(false);
		sentBtn.addActionListener(e ->
		{
			client.sentMessage(sendTextField.getText());
			sendTextField.setText("");
		});

		connectBtn = new JButton("連線");
		frame.add(connectBtn, BorderLayout.LINE_START);
		connectBtn.addActionListener(e ->
		{
			connectBtnOnClick();
		});

		disconnectBtn = new JButton("中斷");
		frame.add(disconnectBtn, BorderLayout.LINE_END);
		disconnectBtn.setEnabled(false);
		disconnectBtn.addActionListener(e ->
		{
			disconnectBtnOnClick();
		});

		getMessageTextField = new TextField("");
		frame.add(getMessageTextField, BorderLayout.PAGE_END);
		getMessageTextField.setEditable(false);
		thread.start();
	}
	public static void connectBtnOnClick()
	{
		client.runClient();
		setDisconnectBtnEnable(true);
		setConnectBtnEnable(false);
		setSendTextFieldEnable(true);
		setSentBtnEnable(true);
	}

	public static void disconnectBtnOnClick()
	{
		client.disconnectFlag();
		setDisconnectBtnEnable(false);
		setConnectBtnEnable(true);
		setSendTextFieldEnable(false);
		setSentBtnEnable(false);
	}
	public static void setDisconnectBtnEnable(boolean bool)
	{
		disconnectBtn.setEnabled(bool);
	}
	public static void setConnectBtnEnable(boolean bool)
	{
		connectBtn.setEnabled(bool);
	}
	public static void setSentBtnEnable(boolean bool)
	{
		sentBtn.setEnabled(bool);
	}
	public static void setSendTextFieldEnable(boolean bool)
	{
		sendTextField.setEnabled(bool);
	}

	public static void displayGetMessage(String Message)
	{
		getMessageTextField.setText(Message);
	}
}

