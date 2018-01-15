package ui;

import recorder.RecorderInterfaceListener;

public interface RecorderButton {
    public void executeRecorderFunction(RecorderInterfaceListener recorderInterfaceListener);
    public boolean hitTest(double x, double y);
}
