package rs.raf.projekat2.nemanja_tesic_30_17.extensions

import android.content.res.Resources

fun Int.toDp() : Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx() : Int = (this * Resources.getSystem().displayMetrics.density).toInt()