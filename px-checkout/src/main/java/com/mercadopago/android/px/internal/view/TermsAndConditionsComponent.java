package com.mercadopago.android.px.internal.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.mercadopago.android.px.R;
import com.mercadopago.android.px.internal.features.TermsAndConditionsActivity;
import com.mercadopago.android.px.internal.features.payment_result.components.LineSeparator;
import com.mercadopago.android.px.internal.features.review_and_confirm.models.TermsAndConditionsModel;
import com.mercadopago.android.px.internal.util.ViewUtils;

public class TermsAndConditionsComponent extends CompactComponent<TermsAndConditionsModel, Void> {

    public TermsAndConditionsComponent(@NonNull final TermsAndConditionsModel props) {
        super(props);
    }

    @Override
    public View render(@NonNull final ViewGroup parent) {
        final Context context = parent.getContext();
        final LinearLayout linearContainer = ViewUtils.createLinearContainer(context);

        final View termsAndConditionsView = ViewUtils.inflate(parent, R.layout.px_view_terms_and_conditions);
        final MPTextView mTermsAndConditionsMessageView =
            termsAndConditionsView.findViewById(R.id.terms_and_conditions_message);
        final MPTextView mTermsAndConditionsLinkView =
            termsAndConditionsView.findViewById(R.id.terms_and_conditions_link);

        mTermsAndConditionsMessageView.setText(props.getMessage());
        mTermsAndConditionsLinkView.setText(props.getMessageLinked());

        termsAndConditionsView.setOnClickListener(view -> TermsAndConditionsActivity.start(context, props.getUrl()));

        final LineSeparator lineSeparator = new LineSeparator(new LineSeparator.Props(R.color.px_med_light_gray));

        switch (props.getLineSeparatorType()) {
        case TOP_LINE_SEPARATOR:
            linearContainer.addView(lineSeparator.render(linearContainer));
            linearContainer.addView(termsAndConditionsView);
            break;
        case BOTTOM_LINE_SEPARATOR:
            linearContainer.addView(termsAndConditionsView);
            linearContainer.addView(lineSeparator.render(linearContainer));
            break;
        default:
            linearContainer.addView(termsAndConditionsView);
            break;
        }

        return linearContainer;
    }
}