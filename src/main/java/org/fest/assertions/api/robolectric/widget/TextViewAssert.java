package org.fest.assertions.api.robolectric.widget;

import android.graphics.drawable.BitmapDrawable;
import android.widget.TextView;
import org.fest.assertions.api.Assertions;
import org.fest.assertions.api.android.widget.AbstractTextViewAssert;
import org.fest.assertions.api.robolectric.graphics.drawable.BitmapDrawableAssert;

public class TextViewAssert extends AbstractTextViewAssert<TextViewAssert, TextView> {

    public TextViewAssert(TextView actual) {
        super(actual, TextViewAssert.class);
    }

    public TextViewAssert hasDrawableWithId(int resId) {
        isNotNull();
        boolean hasDrawable = BitmapDrawableAssert.hasDrawableResourceId(resId,
            (BitmapDrawable[]) actual.getCompoundDrawables());
        Assertions.assertThat(hasDrawable)
            .overridingErrorMessage("Expected TextView to have drawable with id <%d>", resId).isTrue();
        return this;
    }
}
