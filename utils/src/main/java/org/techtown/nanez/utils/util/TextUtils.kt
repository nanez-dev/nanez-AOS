package org.techtown.nanez.utils.util

import java.text.DecimalFormat


fun Int.toPrice(): String = StringBuilder().append(DecimalFormat("###,###").format(this)).append("원").toString()