package org.jboss.reddeer.swt.impl.shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.jboss.reddeer.common.logging.Logger;
import org.jboss.reddeer.swt.api.Shell;
import org.jboss.reddeer.core.condition.ShellWithTextIsActive;
import org.jboss.reddeer.core.condition.ShellWithTextIsAvailable;
import org.jboss.reddeer.swt.exception.SWTLayerException;
import org.jboss.reddeer.core.handler.ShellHandler;
import org.jboss.reddeer.core.handler.WidgetHandler;
import org.jboss.reddeer.swt.impl.button.CancelButton;
import org.jboss.reddeer.common.wait.WaitUntil;
import org.jboss.reddeer.common.wait.WaitWhile;

/**
 * Abstract class for all Shells
 * 
 * @author Jiri Peterka
 * 
 */
public abstract class AbstractShell implements Shell {

	private static final Logger log = Logger.getLogger(AbstractShell.class);

	protected org.eclipse.swt.widgets.Shell swtShell;

	protected AbstractShell(org.eclipse.swt.widgets.Shell swtShell) {
		if (swtShell != null) {
			this.swtShell = swtShell;
		} else {
			throw new SWTLayerException("SWT Shell passed to constructor is null");
		}
	}

	@Override
	public Control getControl() {
		return swtShell;
	}

	@Override
	public String getText() {
		String text = WidgetHandler.getInstance().getText(swtShell);
		return text;
	}

	@Override
	public void setFocus() {
		String text = getText();
		log.debug("Set focus to Shell " + text);
		WidgetHandler.getInstance().setFocus(swtShell);
		new WaitUntil(new ShellWithTextIsActive(text));
	}
	
	@Override
	public boolean isVisible(){
		return ShellHandler.getInstance().isVisible(swtShell);
	}
	
	@Override
	public boolean isFocused() {
		return ShellHandler.getInstance().isFocused(swtShell);
	}

	@Override
	public void close() {
		String text = getText();
		log.info("Close shell " + text);
		try {
			new CancelButton().click();
		} catch (Exception e) {
			WidgetHandler.getInstance().notify(SWT.Close, swtShell);
			ShellHandler.getInstance().closeShell(swtShell);
		}
		new WaitWhile(new ShellWithTextIsAvailable(text));
	}

	public org.eclipse.swt.widgets.Shell getSWTWidget() {
		return swtShell;
	}

	@Override
	public boolean isEnabled() {
		return WidgetHandler.getInstance().isEnabled(swtShell);
	}

}
