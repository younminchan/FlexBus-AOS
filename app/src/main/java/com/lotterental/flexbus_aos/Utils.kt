package com.lotterental.flexbus_aos

import android.content.Context
import android.text.InputFilter
import android.view.inputmethod.InputMethodManager
import java.util.regex.Pattern


object Utils {

    /** 키보드 내리기*/
    fun hideKeyboard(){
        val imm = App.activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(App.activity.window.decorView.applicationWindowToken, 0)
    }

    /** 문자열필터(EditText Filter) */
    var filterAlphaNumSpace = InputFilter { source, start, end, dest, dstart, dend ->
        /*
            [요약 설명]
            1. 정규식 패턴 ^[a-z] : 영어 소문자 허용
            2. 정규식 패턴 ^[A-Z] : 영어 대문자 허용
            3. 정규식 패턴 ^[ㄱ-ㅣ가-힣] : 한글 허용
            4. 정규식 패턴 ^[0-9] : 숫자 허용
            5. 정규식 패턴 ^[ ] or ^[\\s] : 공백 허용
        */
        val ps = Pattern.compile("^[ㄱ-ㅣ가-힣a-zA-Z0-9\\s]+$")
        if (!ps.matcher(source).matches()) {
          ""
        } else source
    }

}