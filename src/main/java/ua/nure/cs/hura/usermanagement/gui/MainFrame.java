package ua.nure.cs.hura.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ua.nure.cs.hura.usermanagement.domain.User;
import ua.nure.cs.hura.usermanagement.db.DaoFactory;
import ua.nure.cs.hura.usermanagement.db.Dao;
import ua.nure.cs.hura.usermanagement.util.Messages;

public class MainFrame extends JFrame {
	
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 800;
	private Dao dao;
	private JPanel contentPanel;
	private BrowsePanel browsePanel;
	private AddPanel addPanel;
	private DetailsPanel detailsPanel;
    private EditPanel editPanel;
	
	public MainFrame() {
		super();
		dao = DaoFactory.getInstance().getUserDao();
		initialize();
	}
	
	public Dao getDao() {
        return dao;
    }

	private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(Messages.getString("MainFrame.user_management")); //$NON-NLS-1$
        this.setContentPane(getContentPanel());
    }
	
	/**
     * @param args
     */
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);

    }
	
	public void showAddPanel() {
		showPanel(getAddPanel());
	}
	

	private void showPanel(JPanel panel) {
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		panel.repaint();
		
	}

	private AddPanel getAddPanel() {
		if(addPanel == null) {
			addPanel = new AddPanel(this);//Implement Later
		}
		return addPanel;
	}
	
	public void showBrowsePanel() {
        showPanel(getBrowsePanel());
        
    }


    public void showEditPanel(User user) {

        getEditPanel().setUser(user);//Impl Later
        showPanel(getEditPanel());
    }


    private EditPanel getEditPanel() {
        if (editPanel == null) {
            editPanel = new EditPanel(this);//Impl Later
        }
        return editPanel;
    }

    public void showDetailsPanel(User user) {
        getDetailsPanel().setUser(user);//Impl Later
        showPanel(getDetailsPanel());
    }

    private DetailsPanel getDetailsPanel() {
        if (detailsPanel == null) {
            detailsPanel = new DetailsPanel(this);//Impl Later
        }
        return detailsPanel;
    }

	private JPanel getContentPanel() {
        if (contentPanel == null) {
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
        }
        return contentPanel;
    }
	

	private JPanel getBrowsePanel() {
        if (browsePanel == null) {
            browsePanel = new BrowsePanel(this);
        }
        ((BrowsePanel) browsePanel).initTable();
        return browsePanel;
    }

}
