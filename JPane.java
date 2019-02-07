
//@author Halil Ibryam B6033541
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class JPane extends JEditorPane implements HyperlinkListener {
	Toolbar toolBar;

	public Toolbar getBar() {
		return toolBar;
	}

	public void setBar(Toolbar bar) {
		this.toolBar = bar;
	}

	public JPane(String homeurl) throws IOException {
		super(homeurl); // call to the constructor of superclass
		this.addHyperlinkListener(this);
		this.setEditable(false);
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		String history = "history.txt";
		try {

			if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
				this.setPage(e.getURL());

				this.toolBar.getTextField().setText(e.getURL().toString());
				BufferedWriter writer = new BufferedWriter(new FileWriter(history, true));
				writer.write(toolBar.getTextField().getText());
				writer.newLine();
				writer.close();
				Toolbar.currentIndex++;
				Toolbar.urls.add(e.getURL().toString());
				toolBar.backEnable(toolBar);
				toolBar.forwEnable(toolBar);
			}
		} catch (Exception e1) {

		}

	}

}