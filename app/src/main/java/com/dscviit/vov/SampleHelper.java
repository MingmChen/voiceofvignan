package com.dscviit.vov;

import android.app.Activity;
import android.content.Intent;
import android.widget.FrameLayout;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

public class SampleHelper  {

    private Activity activity;

    private SampleHelper(Activity activity) {
        this.activity = activity;
    }

    public static SampleHelper with(Activity activity) {
        return new SampleHelper(activity);
    }


    public void loadAbout() {
        final FrameLayout flHolder = activity.findViewById(R.id.about);

        AboutBuilder builder = AboutBuilder.with(activity)
                .setAppIcon(R.mipmap.ic_launcher_foreground)
                .setAppName(R.string.app_name)
                .setPhoto(R.mipmap.devimg)
                .setCover(R.color.placeholder_bg)
                .setLinksAnimated(true)
                .setDividerDashGap(13)
                .setName("Venkatapathi Raju Gangiri")
                .setSubTitle("Android Enthusiast")
                .setLinksColumnsCount(4)
                .setBrief("Lead - Google Developer Student Club,Tech geek,Engineer by knowledge | Entrepreneur by thoughts")
                .addGooglePlayStoreLink("7421646226358389189")
                .addGitHubLink("techpathi")
                .addFacebookLink("venkatapathirajug")
                .addInstagramLink("soul_on_sale")
                .addLinkedInLink("venkatapathirajug")
                .addEmailLink("venkat4coding@gmail.com")
                .addWhatsappLink("Venkat", "+919573764574")
                .addWebsiteLink("www.about.me/venkatapathirajug")
                .addFiveStarsAction()
                .addMoreFromMeAction("Venkatapathi Raju Gangiri")
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .addUpdateAction()
                .setActionsColumnsCount(2)
                .addFeedbackAction("venkat4coding@gmail.com")
                .addPrivacyPolicyAction("http://www.docracy.com/2d0kis6uc2")
                .addIntroduceAction((Intent) null)
                .addHelpAction((Intent) null)
                .addChangeLogAction((Intent) null)
                .addRemoveAdsAction((Intent) null)
                .addDonateAction((Intent) null)
                .setWrapScrollView(true)
                .setShowAsCard(true);

        AboutView view = builder.build();

        flHolder.addView(view);
    }



}
