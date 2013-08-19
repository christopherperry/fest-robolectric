
package org.fest.assertions.api.robolectric;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.fest.assertions.api.robolectric.view.ViewGroupAssert;
import org.fest.assertions.api.robolectric.widget.ImageViewAssert;
import org.fest.assertions.api.robolectric.widget.TextViewAssert;

public class ROBOFEST {

    public static ImageViewAssert assertThat(ImageView actual) {
        return new ImageViewAssert(actual);
    }

    public static TextViewAssert assertThat(TextView actual) {
        return new TextViewAssert(actual);
    }

    public static ViewGroupAssert assertThat(ViewGroup actual) {
        return new ViewGroupAssert(actual);
    }

    protected ROBOFEST() {

    }
}
