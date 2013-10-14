fun is_older(first_date: (int*int*int), second_date: (int*int*int)) =
    if #1 first_date < #1 second_date 
    then true
    else 
	if #1 first_date = #1 second_date 
	then 
	    if #2 first_date < #2 second_date
	    then true
	    else
		if #2 first_date = #2 second_date
		then #3 first_date < #3 second_date
		else false
	else false
										
fun number_in_month(dates: (int*int*int) list, month: int) = 
    if null dates
    then 0
    else number_in_month(tl dates, month) + (if (#2 (hd dates)) = month then 1 else 0)

fun number_in_months(dates: (int*int*int) list, months: int list) = 
    if null months
    then 0
    else number_in_month(dates, hd months) + number_in_months(dates, tl months)

fun dates_in_month(dates: (int*int*int) list, month: int) = 
    if null dates
    then []
    else if #2 (hd dates) = month then (hd dates)::dates_in_month(tl dates, month) else dates_in_month(tl dates, month)

fun dates_in_months(dates: (int*int*int) list, months: int list) =
    let 
	fun concat(list1: (int*int*int) list, list2: (int*int*int) list) = 
	    if null list1
	    then list2
	    else (hd list1)::concat(tl list1, list2)
    in
	if null months
	then []
	else concat(dates_in_month(dates, hd months), dates_in_months(dates, tl months))
    end

fun get_nth(strings: string list, number: int) = 
    if number = 1 
    then hd strings
    else get_nth(tl strings, number - 1)

fun date_to_string(date: (int*int*int)) = 
    get_nth(["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"], #2 date)^" "^Int.toString(#3 date)^", "^Int.toString(#1 date)

fun number_before_reaching_sum(sum: int(*assume positive*), list_numbers: int list) =
    let 
	fun number_aux(current_sum: int, counter: int, list_num: int list) = 
	    if current_sum < sum then number_aux(current_sum + (hd list_num), counter + 1, tl list_num) else counter - 1
    in
	number_aux(0, 0, list_numbers)
    end

fun what_month(day: int) = 
    number_before_reaching_sum(day, [31,28,31,30,31,30,31,31,30,31,30,31]) + 1

fun month_range(day1: int, day2: int) = 
    if day1 > day2 
    then [] 
    else what_month(day1)::month_range(day1 + 1, day2)

fun oldest(dates: (int*int*int) list) = 
    if null dates 
    then NONE
    else if null (tl dates) then SOME(hd dates)
    else let
	val old = oldest(tl dates)
    in
	if is_older(hd dates, valOf old) then SOME(hd dates) else old
    end

fun number_in_months_challenge(dates: (int*int*int) list, months: int list) = 
    let
	fun remove_duplicates(months: int list) = 
	    let
		fun exists(month: int, lst: int list) = 
		    if null lst 
		    then false
		    else (hd lst) = month orelse exists(month, tl lst)
	    in
		if null months 
		then []
		else 
		    if exists(hd months, tl months) 
		    then remove_duplicates(tl months) 
		    else (hd months)::remove_duplicates(tl months)
	    end
    in
	number_in_months(dates, remove_duplicates months)
    end

fun dates_in_months_challenge(dates: (int*int*int) list, months: int list) = 
    let
	fun remove_duplicates_aux(current: int list, found: int list) = 
	    let
		fun exists(month: int, lst: int list) = 
		    if null lst 
		    then false
		    else (hd lst) = month orelse exists(month, tl lst)
	    in
		if null current
		then []
		else 
		    if exists(hd current, found) 
		    then remove_duplicates_aux(tl current, found) 
		    else (hd current)::remove_duplicates_aux(tl current, (hd current):: found)
	    end
	fun remove_duplicates(lst: int list) = remove_duplicates_aux(lst, [])
    in
	dates_in_months(dates, remove_duplicates months)
    end

fun reasonable_date(date: (int*int*int)) =
    let 
	fun check_days(dt: (int*int*int)) = 
	    let 
		fun exists(month: int, lst: int list) = 
		    if null lst 
		    then false
		    else (hd lst) = month orelse exists(month, tl lst)
		fun is_thirtyone(month: int) = exists(month, [1, 3, 5, 7, 8, 10, 12])
	        fun is_thirty(month: int) = exists(month, [4, 6, 9, 11])
		fun is_february(month: int) = month = 2
	    in 
		if is_thirtyone(#2 dt) 
		then (#3 dt) < 32 
		else 
		    if is_thirty(#2 dt) 
		    then (#3 dt) < 31
		    else 
			if is_february(#2 dt) 
			then 
			    if (((#1 dt) mod 400) = 0 orelse ((#1 dt) mod 4) = 0 andalso not (((#1 dt) mod 100) = 0))
			    then (#3 dt) < 30 
			    else (#3 dt) < 29
			else false
	    end
    in
	#2 date > 0 andalso #2 date < 13 andalso #1 date > 0 andalso #3 date > 0 andalso check_days(date)
    end
    

