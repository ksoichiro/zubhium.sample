/*
 * Copyright (c) 2012 Soichiro Kashima
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.blogspot.ksoichiro.android.zubhium.sample.util;

import com.blogspot.ksoichiro.android.zubhium.sample.R;
import com.zubhium.ZubhiumSDK;
import com.zubhium.ZubhiumSDK.ZubhiumFeedbackCategory;
import com.zubhium.utils.ZubhiumUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <p>
 * Utility class for using Zubhium custom feedback dialog.
 * </p>
 * <p>
 * At first, you must modify {@link #ZUBHIUM_APP_SECRET_KEY} to your own
 * application's secret key.
 * </p>
 * <p>
 * You can customize the dialog via res/layout/dialog_feedback.xml and
 * res/values/strings_zubhium.xml.
 * </p>
 * 
 * @author ksoichiro
 */
public final class FeedbackUtils {

    /**
     * Zubhium application secret key. You MUST modify this value before using
     * this class.
     */
    private static final String ZUBHIUM_APP_SECRET_KEY = "883ecc32c826daf33aef9454c207fc";

    /**
     * Hide constructor because this is an utility class.
     */
    private FeedbackUtils() {
    }

    /**
     * Get Zubhium SDK instance.
     * 
     * @param context context to access to the application
     * @return SDK instance
     */
    public static ZubhiumSDK getZubhiumSDKInstance(final Context context) {
        return ZubhiumSDK.getZubhiumSDKInstance(
                context, ZUBHIUM_APP_SECRET_KEY);
    }

    /**
     * Send feedback to the Zubhium.
     * 
     * @param context context to access to the application
     */
    public static void sendFeedback(final Context context) {
        final ZubhiumSDK sdk = getZubhiumSDKInstance(context);
        if (sdk != null) {
            // Setting up the feedback dialog content
            final View view = ((LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.dialog_feedback, null);
            ((TextView) view.findViewById(R.id.app_name)).setText(
                    ZubhiumUtils.getAppName(context));
            ((TextView) view.findViewById(R.id.app_version)).setText(
                    "version - " + ZubhiumUtils.getAppVersion(context));

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(android.R.drawable.ic_dialog_info);
            builder.setTitle(R.string.zubhium_dialog_title_feedback);
            builder.setView(view);
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialog, final int which) {
                    String email = ((EditText) view.findViewById(R.id.email))
                            .getText().toString();
                    // Anonymous feedback is welcomed!
                    if (TextUtils.isEmpty(email)) {
                        email = "";
                    }

                    String feedback = ((EditText) view.findViewById(R.id.feedback_message))
                            .getText().toString();
                    // We allow feedbacks without messages.
                    if (TextUtils.isEmpty(feedback)) {
                        feedback = "";
                    }

                    RadioGroup category = (RadioGroup) view.findViewById(R.id.category);
                    ZubhiumFeedbackCategory feedbackCategory;
                    switch (category.getCheckedRadioButtonId()) {
                        case R.id.category_bug:
                            feedbackCategory = ZubhiumFeedbackCategory.BUG;
                            break;
                        case R.id.category_feature:
                            feedbackCategory = ZubhiumFeedbackCategory.FEATURE;
                            break;
                        case R.id.category_other:
                        default:
                            feedbackCategory = ZubhiumFeedbackCategory.OTHER;
                            break;
                    }

                    boolean includeSystemLogs = ((CheckBox) view
                            .findViewById(R.id.include_system_logs)).isChecked();

                    Toast.makeText(context,
                            R.string.zubhium_msg_sending_feedback,
                            Toast.LENGTH_SHORT).show();
                    sdk.submitFeedback(email, feedback, feedbackCategory,
                            includeSystemLogs);
                }
            });
            builder.setNegativeButton(android.R.string.cancel, null);
            builder.show();
        }
    }

}
