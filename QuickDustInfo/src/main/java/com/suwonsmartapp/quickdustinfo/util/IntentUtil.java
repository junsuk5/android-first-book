package com.suwonsmartapp.quickdustinfo.util;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by junsuk on 2017. 9. 8..
 */

public class IntentUtil {
    // 소스 코드
    public static Intent getSourceCodeIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/junsuk5/android-first"));
    }

    public static Intent getBuyBookIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.yes24.com/"));
    }

    public static Intent getFacebookPageIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/untilandroid"));
    }

    public static Intent getPublisherIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("https://rubypaper.tistory.com/"));
    }

    public static Intent getHomepageIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.suwonsmartapp.com"));
    }

    public static Intent getLocationIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.274105,127.02262100000007(세민직업전문학교)?z=11"));
    }

    public static Intent getDialPhoneIntent() {
        return new Intent(Intent.ACTION_DIAL, Uri.parse("tel:031-236-5040"));
    }


}
