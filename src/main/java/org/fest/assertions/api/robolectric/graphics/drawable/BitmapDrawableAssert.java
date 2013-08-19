package org.fest.assertions.api.robolectric.graphics.drawable;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import org.fest.assertions.api.Assertions;
import org.fest.assertions.api.android.graphics.drawable.AbstractDrawableAssert;
import org.robolectric.shadows.ShadowBitmap;

import static org.robolectric.bytecode.ShadowWrangler.shadowOf;

public class BitmapDrawableAssert extends AbstractDrawableAssert<BitmapDrawableAssert, BitmapDrawable> {

    protected BitmapDrawableAssert(BitmapDrawable actual) {
        super(actual, BitmapDrawableAssert.class);
    }

    public org.fest.assertions.api.android.graphics.drawable.BitmapDrawableAssert hasResourceId(int resourceId) {
        isNotNull();
        boolean hasResourceId = hasDrawableResourceId(resourceId, actual);
        Assertions.assertThat(hasResourceId)
            .overridingErrorMessage("Expected BitmapDrawable to have been created with id <%d>", resourceId)
            .isTrue();
        return new org.fest.assertions.api.android.graphics.drawable.BitmapDrawableAssert(actual);
    }

    public static boolean hasDrawableResourceId(int resourceId, BitmapDrawable... drawables) {
        for (BitmapDrawable drawable : drawables) {
            if (drawable == null) {
                continue;
            }

            Bitmap bitmap = drawable.getBitmap();
            ShadowBitmap shadowBitmap = (ShadowBitmap) shadowOf(bitmap);
            int createdFromResId = shadowBitmap.getCreatedFromResId();
            if (createdFromResId == resourceId) {
                return true;
            }
        }
        return false;
    }
}
