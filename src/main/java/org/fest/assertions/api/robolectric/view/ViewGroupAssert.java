package org.fest.assertions.api.robolectric.view;

import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.TextView;
import org.fest.assertions.api.Assertions;
import org.fest.assertions.api.android.view.AbstractViewGroupAssert;
import org.fest.assertions.api.robolectric.graphics.drawable.BitmapDrawableAssert;
import org.fest.assertions.core.Condition;

public class ViewGroupAssert extends AbstractViewGroupAssert<ViewGroupAssert, ViewGroup> {

    public ViewGroupAssert(ViewGroup actual) {
        super(actual, ViewGroupAssert.class);
    }

    public ViewGroupAssert hasTextViewWithText(String text) {
        isNotNull();
        boolean found = walkTree(actual, new TextMatcher(text));
        Assertions.assertThat(found)
            .overridingErrorMessage("Expected ViewGroup to have a TextView with text <%s>", text).isTrue();
        return this;
    }

    public ViewGroupAssert hasTextViewWithCompoundDrawableId(int resId) {
        isNotNull();
        boolean found = walkTree(actual, new TextViewCompoundDrawableCondition(resId));
        Assertions.assertThat(found)
            .overridingErrorMessage("Expected ViewGroup to have a TextView with resId <%d>", resId).isTrue();
        return this;
    }

    private static class TextMatcher extends Condition<TextView> {
        private String text;

        private TextMatcher(String text) {
            this.text = text;
        }

        @Override public boolean matches(TextView textView) {
            String actualText = textView.getText().toString();
            return actualText.equals(text);
        }
    }

    private static class TextViewCompoundDrawableCondition extends Condition<TextView> {
        private final int resourceId;

        public TextViewCompoundDrawableCondition(int resourceId) {
            this.resourceId = resourceId;
        }

        @Override public boolean matches(TextView textView) {
            return BitmapDrawableAssert.hasDrawableResourceId(resourceId,
                (BitmapDrawable[]) textView.getCompoundDrawables());
        }
    }

    private boolean walkTree(View view, Condition<TextView> condition) {
        if (view instanceof TextView) {
            if (condition.matches((TextView) view)) {
                return true;
            }
        } else if (view instanceof AdapterView) {
            Adapter adapter = ((AdapterView) view).getAdapter();
            for (int i = 0; i < adapter.getCount(); i++) {
                View adapterView = adapter.getView(i, null, null);
                if (walkTree(adapterView, condition)) {
                    return true;
                }
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childView = viewGroup.getChildAt(i);
                if (childView instanceof ViewGroup) {
                    if (walkTree(childView, condition)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
