package org.fest.assertions.api.robolectric.widget;

import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import org.fest.assertions.api.Assertions;
import org.fest.assertions.api.android.view.AbstractViewAssert;
import org.fest.assertions.api.robolectric.graphics.drawable.BitmapDrawableAssert;

public class ImageViewAssert extends AbstractViewAssert<ImageViewAssert, ImageView> {

    public ImageViewAssert(ImageView actual) {
        super(actual, ImageViewAssert.class);
    }

    public ImageViewAssert hasDrawableWithId(int resId) {
        boolean hasDrawable = BitmapDrawableAssert.hasDrawableResourceId(
            resId, (BitmapDrawable) actual.getDrawable());
        Assertions.assertThat(hasDrawable)
            .overridingErrorMessage("Expected ImageView to have drawable with id <%d>", resId).isTrue();
        return this;
    }
}
