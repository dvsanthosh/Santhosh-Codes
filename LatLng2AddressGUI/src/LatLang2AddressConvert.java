import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.json.JSONException;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.wb.swt.SWTResourceManager;



public class LatLang2AddressConvert {

	protected Shell shlBatchLatLang;
	private Text input;
	private Text output;
	

	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
	    
		try {
			LatLang2AddressConvert window = new LatLang2AddressConvert();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlBatchLatLang.open();
		shlBatchLatLang.layout();
		while (!shlBatchLatLang.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
	
		shlBatchLatLang = new Shell();
		shlBatchLatLang.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		shlBatchLatLang.setSize(450, 450);
		shlBatchLatLang.setText("Lat Lang to Address Batch Converter");
		
		MessageBox msg = new MessageBox(shlBatchLatLang);
		msg.setMessage("Input and output files must be Comma Seperated Files(.csv) \nInput Must have Lattitude as first value and Longitude as Second value seperated by comma") ;
		msg.open();
		
		Button btnStartConversion = new Button(shlBatchLatLang, SWT.NONE);
		btnStartConversion.setBounds(115, 72, 99, 25);
		btnStartConversion.setText("Start Conversion");
		btnStartConversion.setEnabled(false);
		
		Label lblNewLabel = new Label(shlBatchLatLang, SWT.NONE);
		lblNewLabel.setBounds(10, 20, 66, 15);
		lblNewLabel.setText("Lat Lang File");
		
		input = new Text(shlBatchLatLang, SWT.BORDER);
		input.setEnabled(false);
		input.setBounds(82, 14, 299, 21);
		
		Button inButton = new Button(shlBatchLatLang, SWT.NONE);
		inButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					JFileChooser fc = new JFileChooser();
					int returnVal = fc.showOpenDialog(fc);

		            if (returnVal == JFileChooser.APPROVE_OPTION) {
		            	input.setText(fc.getSelectedFile().toString());
		            	if(!(input.getText().trim().isEmpty() || output.getText().trim().isEmpty()))
		            		btnStartConversion.setEnabled(true);
		            }
				}catch (Exception e1){e1.printStackTrace();}
			}
		});
		inButton.setToolTipText("Browse Input");
		inButton.setBounds(387, 10, 37, 25);
		inButton.setText("...");
		
		Label lblSaveResult = new Label(shlBatchLatLang, SWT.NONE);
		lblSaveResult.setText("Save Result");
		lblSaveResult.setBounds(10, 48, 66, 15);
		
		output = new Text(shlBatchLatLang, SWT.BORDER);
		output.setEnabled(false);
		output.setBounds(82, 45, 299, 21);
		
		Button outButton = new Button(shlBatchLatLang, SWT.NONE);
		outButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					JFileChooser fc = new JFileChooser();
					fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					int returnVal = fc.showSaveDialog(fc);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
		            	output.setText(fc.getSelectedFile().toString());
		            	if(!(input.getText().trim().isEmpty() || output.getText().trim().isEmpty()))
		            		btnStartConversion.setEnabled(true);

		            }
				}catch (Exception e1){e1.printStackTrace();}
				
			}
		});
		outButton.setToolTipText("Browse Output");
		outButton.setText("...");
		outButton.setBounds(387, 41, 37, 25);
		
		ProgressBar progressBar = new ProgressBar(shlBatchLatLang, SWT.NONE);
		progressBar.setBounds(10, 128, 414, 17);



		//progressBar.
		
		Button btnOpenResult = new Button(shlBatchLatLang, SWT.NONE);
		btnOpenResult.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				File outputFile = new File(LatLang2AddressConvert.this.output.getText());
				try{
					Desktop.getDesktop().open(outputFile);
				}catch(Exception e2){}
				
			}
		});
		btnOpenResult.setText("Open OutPut");
		btnOpenResult.setBounds(258, 72, 99, 25);
		btnOpenResult.setEnabled(false);
		
		final Text consoleOutput = new Text(shlBatchLatLang, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		consoleOutput.setEditable(false);
		consoleOutput.setBounds(10, 172, 414, 229);

		
		btnStartConversion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					//consoleOutput.append("Reading from: "+ LatLang2AddressConvert.this.input.getText()+"\n");
					Convert convert = new Convert();
					boolean completed = convert.batchConvert(LatLang2AddressConvert.this.input.getText(),LatLang2AddressConvert.this.output.getText(),consoleOutput, progressBar);
					if(completed){
						btnOpenResult.setEnabled(true);
					}
				} catch (IOException | JSONException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

		
		Label lblInProgress = new Label(shlBatchLatLang, SWT.NONE);
		lblInProgress.setText("Status:");
		lblInProgress.setBounds(10, 107, 181, 15);
		
		Label lblInTheBack = new Label(shlBatchLatLang, SWT.NONE);
		lblInTheBack.setBounds(10, 151, 117, 15);
		lblInTheBack.setText("In the Back Ground...");

	}
}
