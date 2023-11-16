package com.example.sparkbraille.Translation

class Translation {
    private var _exception = false
    private var CP_CON = false

    private val _capital_prefix = 32
    private val _capital_continue = 34 //(ENGLISH+_capital_prefix) // tracks if there is more than one consecutive capital letters
    private val CP_CON_termination = 4 // terminates capital continue

    private val _letter_sign = 48 // if a letter comes right after a number like (4a)
    private val _number_continue = 62

    private val English_G1to1mod = 35
    private val English_G1to1 = arrayOf(intArrayOf(0, 3, 0), intArrayOf(1, 4, 22), intArrayOf(69, 5, 17), intArrayOf(70, 6, 11), intArrayOf(71, 8, 27), intArrayOf(72, 9, 19), intArrayOf(73, 10, 10), intArrayOf(7, 11, 4), intArrayOf(74, 15, 26), intArrayOf(75, 16, 5), intArrayOf(76, 17, 7), intArrayOf(77, 18, 13), intArrayOf(12, 19, 2), intArrayOf(13, 20, 36), intArrayOf(14, 21, 50), intArrayOf(78, 22, 29), intArrayOf(79, 23, 21), intArrayOf(80, 24, 15), intArrayOf(81, 25, 31), intArrayOf(82, 28, 23), intArrayOf(83, 29, 14), intArrayOf(84, -1, 30), intArrayOf(85, -1, 37), intArrayOf(86, -1, 39), intArrayOf(87, -1, 58), intArrayOf(88, -1, 45), intArrayOf(26, -1, 18), intArrayOf(27, -1, 6), intArrayOf(89, -1, 61), intArrayOf(90, -1, 53), intArrayOf(65, -1, 1), intArrayOf(31, 32, 38), intArrayOf(66, 33, 3), intArrayOf(67, 34, 9), intArrayOf(68, 2, 25))

    //private static Int16 _number_prefix = 60; already in Bangla
    private val English_np_1to2mod = 10
    private val English_np_1to2 = arrayOf(intArrayOf(20, -1, 25), intArrayOf(21, -1, 17), intArrayOf(22, -1, 11), intArrayOf(23, -1, 27), intArrayOf(24, -1, 19), intArrayOf(25, -1, 10), intArrayOf(16, -1, 26), intArrayOf(17, -1, 1), intArrayOf(18, -1, 3), intArrayOf(19, -1, 9)) // prefix 60

    private val _msign_prefix1 = 16
    private val English_mp1_1to2mod = 5
    private val English_mp1_1to2 = arrayOf(intArrayOf(10, -1, 20), intArrayOf(11, -1, 22), intArrayOf(29, -1, 46), intArrayOf(8, -1, 35), intArrayOf(9, 2, 28)) //prefix 16

    private val _msign_prefix2 = 8
    private val English_mp2_1to2mod = 7
    private val English_mp2_1to2 = arrayOf(intArrayOf(28, -1, 35), intArrayOf(62, -1, 34), intArrayOf(30, -1, 28), intArrayOf(94, -1, 20), intArrayOf(4, 5, 14), intArrayOf(32, -1, 1), intArrayOf(6, 1, 47)) // prefix 8

    private val _msign_prefix3 = 40
    private val English_mp3_1to2mod = 4
    private val English_mp3_1to2 = arrayOf(intArrayOf(63, -1, 36), intArrayOf(5, 2, 52), intArrayOf(61, -1, 28), intArrayOf(59, 0, 35)) // prefix 40

    private val _msign_prefix4 = 56
    private val English_mp4_1to2mod = 6
    private val English_mp4_1to2 = arrayOf(intArrayOf(60, -1, 33), intArrayOf(91, -1, 35), intArrayOf(92, -1, 51), intArrayOf(3, 5, 57), intArrayOf(15, -1, 12), intArrayOf(93, 4, 28)) // prefix 56


    private val Bangla_G1to1mod = 51
    private val Bangla_G1to1 = arrayOf(intArrayOf(51, -1, 9), intArrayOf(1, -1, 4), intArrayOf(53, -1, 33), intArrayOf(3, -1, 48), intArrayOf(55, -1, 26), intArrayOf(5, 33, 32), intArrayOf(56, 7, 59), intArrayOf(57, 35, 53), intArrayOf(8, 12, 2), intArrayOf(9, 46, 1), intArrayOf(58, 14, 55), intArrayOf(11, 50, 28), intArrayOf(59, 18, 18), intArrayOf(13, -1, 10), intArrayOf(61, 20, 62), intArrayOf(15, -1, 20), intArrayOf(62, 21, 34), intArrayOf(17, -1, 37), intArrayOf(63, 22, 58), intArrayOf(19, 23, 51), intArrayOf(65, 24, 50), intArrayOf(67, -1, 63), intArrayOf(69, 25, 60), intArrayOf(70, -1, 50), intArrayOf(71, 27, 30), intArrayOf(73, -1, 57), intArrayOf(26, 28, 8), intArrayOf(75, -1, 25), intArrayOf(77, 30, 46), intArrayOf(29, -1, 17), intArrayOf(79, -1, 29), intArrayOf(31, -1, 12), intArrayOf(83, -1, 15), intArrayOf(107, -1, 41), intArrayOf(85, -1, 22), intArrayOf(109, -1, 47), intArrayOf(87, -1, 3), intArrayOf(37, -1, 21), intArrayOf(89, -1, 39), intArrayOf(39, -1, 42), intArrayOf(91, -1, 13), intArrayOf(41, -1, 5), intArrayOf(93, -1, 61), intArrayOf(43, -1, 45), intArrayOf(95, -1, 23), intArrayOf(45, -1, 27), intArrayOf(111, -1, 14), intArrayOf(47, -1, 35), intArrayOf(99, -1, 7), intArrayOf(49, -1, 44), intArrayOf(113, -1, 19))
    private val Bangla_S1to1mod = 9
    private val Bangla_S1to1 = arrayOf(intArrayOf(0, -1, 20), intArrayOf(123, -1, 28), intArrayOf(2, -1, 37), intArrayOf(125, -1, 10), intArrayOf(4, 6, 51), intArrayOf(14, -1, 17), intArrayOf(22, 1, 21), intArrayOf(16, -1, 12), intArrayOf(24, 3, 42))
    private val Bangla_N1to1mod = 10
    private val _number_prefix = 60
    private val BN_number_continue = 61
    private val Bangla_N1to1 = arrayOf(intArrayOf(80, 1, 3), intArrayOf(90, -1, 27), intArrayOf(82, 3, 9), intArrayOf(92, -1, 19), intArrayOf(84, 5, 25), intArrayOf(94, -1, 10), intArrayOf(76, 7, 26), intArrayOf(86, -1, 17), intArrayOf(78, 9, 1), intArrayOf(88, -1, 11))
    private val _bangla_prefix = 16
    private val Bangla_1to2mod = 3
    private val Bangla_1to2 = arrayOf(intArrayOf(6, 1, 23), intArrayOf(21, 2, 23), intArrayOf(28, -1, 46))
    private val Bangla_3to1mod = 2
    private val Bangla_3to1 = arrayOf(intArrayOf(176, 1, 31), intArrayOf(140, -1, 49))

    private fun getCellvalue(table: Array<IntArray>, hash: IntArray, mod: Int): Int {
        val index = if (hash[0] >= 0) hash[0] else (hash[0] + 32)
        val row = index % mod
        val col = if (index / mod >= table[row][2]) 2 else 1
        return if (table[row][col] >= 0) table[row][col] else table[row][0]
    }

    private fun _setPrefix(type: Int, prefix: Int) {
        _prefix_type = type.toInt()
        _prefix = prefix.toInt()
    }

    private fun checkException(hash: IntArray, outbuffer: IntArray, trackout: IntArray): Int {
        var value: Int = if (hash[0] < 0) (hash[0] + 32) else hash[0]

        if ((_sudo_prefix == _quotes_track) && (value.toInt() != _quotes)) {
            _sudo_prefix = _quotes_continue
        }
        when (value) {
            in 33..58 -> {
                hash[0] = (value + 32)
                if ((_prefix_type or _prefix) == _capital_continue) {
                    if (!CP_CON) {
                        outbuffer[trackout[0].toInt()] = outbuffer[trackout[0] - 1]
                        outbuffer[trackout[0] - 1] = _capital_prefix
                        trackout[0]++
                        CP_CON = true
                    }
                } else {
                    _setPrefix(ENGLISH, _capital_prefix)
                    outbuffer[trackout[0]++.toInt()] = _capital_prefix
                }
                value = -1
            }
            in 65..90 -> {
                if (CP_CON) {
                    outbuffer[trackout[0]++.toInt()] = _capital_prefix
                    outbuffer[trackout[0]++.toInt()] = CP_CON_termination
                    _setPrefix(0, 0)
                    CP_CON = false
                } else if ((_prefix_type or _prefix) == _number_continue) {
                    _setPrefix(0, 0)
                    if (value < 76) {
                        outbuffer[trackout[0]++] = _letter_sign
                    }
                }
                value = -1
            }
            else -> {
                if ((_prefix_type or _prefix) == _capital_continue) {
                    _setPrefix(0, 0)
                    CP_CON = false
                }
                when (value) {
                    10, 13 -> value = 0
                    2 -> if (_sudo_prefix == _quotes_continue) {
                        value = _quotesEnd
                        _sudo_prefix = _NONE
                    } else {
                        _sudo_prefix = _quotes_track
                        value = _quotesBegin
                    }
                    else -> value = -1
                }
            }
        }

        return value
    }

    private fun _get_english_cellvalue(hash: IntArray, outbuffer: IntArray, trackout: IntArray, outsize: Int):Boolean {
        var cellvaluebuf = -1
        cellvaluebuf = checkException(hash, outbuffer, trackout)
        if (cellvaluebuf >= 0) {
            outbuffer[trackout[0]++] = cellvaluebuf
            return true
        }

        cellvaluebuf = getCellvalue(English_G1to1, hash, English_G1to1mod)
        if (cellvaluebuf >= 0) {
            outbuffer[trackout[0]++] = cellvaluebuf
            if (_prefix == _number_prefix) {
                _setPrefix(0, 0)
            }
            return true
        }

        cellvaluebuf = getCellvalue(English_np_1to2, hash, English_np_1to2mod)
        if (cellvaluebuf > 0) {
            if ((_prefix_type or _prefix) == _number_continue) {
                outbuffer[trackout[0]++] = cellvaluebuf
            } else {
                outbuffer[trackout[0]++] = _number_prefix
                outbuffer[trackout[0]++] = cellvaluebuf
                _setPrefix(ENGLISH, _number_prefix)
            }
            return true
        }

        cellvaluebuf = getCellvalue(English_mp1_1to2, hash, English_mp1_1to2mod)
        if (cellvaluebuf > 0) {
            outbuffer[trackout[0]++] = _msign_prefix1
            outbuffer[trackout[0]++] = cellvaluebuf
            _setPrefix(0, 0)
            return true
        }

        cellvaluebuf = getCellvalue(English_mp2_1to2, hash, English_mp2_1to2mod)
        if (cellvaluebuf > 0) {
            outbuffer[trackout[0]++] = _msign_prefix2
            outbuffer[trackout[0]++] = cellvaluebuf
            _setPrefix(0, 0)
            return true
        }

        cellvaluebuf = getCellvalue(English_mp3_1to2, hash, English_mp3_1to2mod)
        if (cellvaluebuf > 0) {
            outbuffer[trackout[0]++] = _msign_prefix3
            outbuffer[trackout[0]++] = cellvaluebuf
            _setPrefix(0, 0)
            return true
        }

        cellvaluebuf = getCellvalue(English_mp4_1to2, hash, English_mp4_1to2mod)
        if (cellvaluebuf > 0) {
            outbuffer[trackout[0]++] = _msign_prefix4
            outbuffer[trackout[0]++] = cellvaluebuf
            _setPrefix(0, 0)
            return true
        }
        return false
    }

    private fun _get_bangla_cellvalue(hash: IntArray, outbuffer: IntArray, trackout: IntArray, outsize: Int):Boolean {
        var cellvaluebuf = -1

        cellvaluebuf = getCellvalue(Bangla_G1to1, hash, Bangla_G1to1mod)
        multicharditection[charditectiontrack++] = hash[0].toShort()
        charditectiontrack = if (charditectiontrack > 2) 0 else charditectiontrack

        if (_sudo_prefix == _quotes_track)
            _sudo_prefix = _quotes_continue

        if (cellvaluebuf > 0) {
            val sudochar = intArrayOf(multicharditection[0] + multicharditection[1] + multicharditection[2])
            val sudocellvaluebuf = getCellvalue(Bangla_3to1, sudochar, Bangla_3to1mod)
            _setPrefix(0, 0)
            if (sudocellvaluebuf > 0) {
                trackout[0] -= 2
                outbuffer[trackout[0]++] = sudocellvaluebuf
                return true
            }
            outbuffer[trackout[0]++] = cellvaluebuf
            return true
        }

        cellvaluebuf = getCellvalue(Bangla_S1to1, hash, Bangla_S1to1mod)
        if (cellvaluebuf > 0) {
            _setPrefix(0, 0)
            outbuffer[trackout[0]++] = cellvaluebuf
            return true
        }

        cellvaluebuf = getCellvalue(Bangla_N1to1, hash, Bangla_N1to1mod)
        if (cellvaluebuf > 0) {
            if ((_prefix_type or _prefix) == BN_number_continue) {
                outbuffer[trackout[0]++] = cellvaluebuf
            } else {
                _setPrefix(BANGLA, _number_prefix)
                outbuffer[trackout[0]++] = _number_prefix
                outbuffer[trackout[0]++] = cellvaluebuf
            }
            return true
        }

        cellvaluebuf = getCellvalue(Bangla_1to2, hash, Bangla_1to2mod)
        if (cellvaluebuf > 0) {
            _setPrefix(0, 0)
            outbuffer[trackout[0]++] = _bangla_prefix
            outbuffer[trackout[0]++] = cellvaluebuf
            return true
        }
        return false
    }

    fun getTranslationBN(hash: IntArray): IntArray {
        val ar = IntArray(2)
        var cellvaluebuf: Int
        cellvaluebuf = getCellvalue(Bangla_G1to1, hash, Bangla_G1to1mod)
        if (cellvaluebuf > 0) {
            ar[0] = cellvaluebuf
            return ar
        }
        cellvaluebuf = getCellvalue(Bangla_3to1, hash, Bangla_3to1mod)
        if (cellvaluebuf > 0) {
            ar[0] = cellvaluebuf
            return ar
        }
        cellvaluebuf = getCellvalue(Bangla_S1to1, hash, Bangla_S1to1mod)
        if (cellvaluebuf > 0) {
            ar[0] = cellvaluebuf
            return ar
        }
        cellvaluebuf = getCellvalue(Bangla_N1to1, hash, Bangla_N1to1mod)
        if (cellvaluebuf > 0) {
            ar[0] = _number_prefix
            ar[1] = cellvaluebuf
            return ar
        }
        cellvaluebuf = getCellvalue(Bangla_1to2, hash, Bangla_1to2mod)
        if (cellvaluebuf > 0) {
            ar[0] = _bangla_prefix
            ar[1] = cellvaluebuf
            return ar
        }
        return ar
    }

    fun getTranslationEN(hash: IntArray): IntArray {
        val ar = IntArray(2)
        var cellvaluebuf: Int =  -1
        if (hash[0] in 33..58) {
            ar[0] = _capital_prefix
        }
        cellvaluebuf = getCellvalue(English_G1to1, hash, English_G1to1mod)
        if (cellvaluebuf > 0) {
            val i = if (ar[0] == _capital_prefix) 1 else 0
            ar[i] = cellvaluebuf
            return ar
        }

        cellvaluebuf = getCellvalue(English_np_1to2, hash, English_np_1to2mod)
        if (cellvaluebuf > 0) {
            ar[0] = _number_prefix
            ar[1] = cellvaluebuf
            return ar
        }

        cellvaluebuf = getCellvalue(English_mp1_1to2, hash, English_mp1_1to2mod)
        if (cellvaluebuf > 0) {
            ar[0] = _msign_prefix1
            ar[1] = cellvaluebuf
            return ar
        }

        cellvaluebuf = getCellvalue(English_mp2_1to2, hash, English_mp2_1to2mod)
        if (cellvaluebuf > 0) {
            ar[0] = _msign_prefix2
            ar[1] = cellvaluebuf
            return ar
        }

        cellvaluebuf = getCellvalue(English_mp3_1to2, hash, English_mp3_1to2mod)
        if (cellvaluebuf > 0) {
            ar[0] = _msign_prefix3
            ar[1] = cellvaluebuf
            return ar
        }

        cellvaluebuf = getCellvalue(English_mp4_1to2, hash, English_mp4_1to2mod)
        if (cellvaluebuf > 0) {
            ar[0] = _msign_prefix4
            ar[1] = cellvaluebuf
            return ar
        }

        if (hash[0] == 2) {
            ar[0] = 38
            ar[1] = 52
        }
        return ar
    }
    ///
    //template<typename _Data_Type>
    fun TranslateString(inbuffer: IntArray, outbuffer: IntArray, insize: Int, outsize: Int, terminationflag: Boolean): IntArray {
        var trackin = intArrayOf(0)
        var trackout = intArrayOf(0)
        while (trackin[0] <= insize && trackout[0] <= outsize) {
            charditection = inbuffer[trackin[0]++].toInt()
            if (charditection > 128) {
                TableMode = BANGLA
                if (trackin[0] > insize) {
                    break
                }
                table_hash = inbuffer[trackin[0]++].toInt()
                if (trackin[0] > insize) {
                    break
                }
                //Console.Write(table_hash+" ");
                table_hash += inbuffer[trackin[0]++] * 2
                table_hash -= 423
                table_hash = if (table_hash < 0) 0 else table_hash
                var hash = intArrayOf(table_hash)
                _get_bangla_cellvalue(hash, outbuffer, trackout, outsize)
            } else {
                table_hash = (charditection - 32).toShort().toInt()
                //Console.Write(table_hash + " ");
                table_hash = if (table_hash < 0) 0 else table_hash
                var hash = intArrayOf(table_hash)
                if (!_get_english_cellvalue(hash, outbuffer, trackout, outsize)) outbuffer[trackout[0]++] = 0
            }
        }
        if (terminationflag) {
            _Set_prefix(0, 0)
            _sudo_prefix = _NONE
            CP_CON = false
        }
        //Console.WriteLine();
        return trackout
    }

    private val BANGLA = 1
    private val ENGLISH = 2
    private var _prefix_type = 0
    private var _prefix = 0
    private var _sudo_prefix = 0
    private val multicharditection = shortArrayOf(0, 0, 0)
    private var charditectiontrack = 0
    private var charditection = 0
    private var table_hash = 0
    private var TableMode = 0
    private val _quotes = 2       // hash value for quotes
    private val _quotesBegin = 38   // anglevalue for quotesbegin
    private val _quotesEnd = 52     // anglevalue for quotesend
    private val _quotes_continue = 2
    private val _quotes_track = 1
    private val _NONE = 0
    private fun _Set_prefix(prefixtype: Short, prefix: Short) {
        _prefix_type = prefixtype.toInt()
        _prefix = prefix.toInt()
    }
    // for using in forward translation
    private fun GetCellvalue(table: Array<ShortArray>, hash: Short, modvalue: Short): Short {
        var key = (hash % modvalue).toInt()
        while (table[key][0] != hash) {
            key = table[key][1].toInt()
            if (key < 0) return -1
        }
        return table[key][2]
    }

}