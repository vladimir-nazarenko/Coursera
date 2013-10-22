(* Dan Grossman, Coursera PL, HW2 Provided Code *)

(* if you use this function to compare two strings (returns true if the same
   string), then you avoid several of the functions in problem 1 having
   polymorphic types that may be confusing *)
fun same_string(s1 : string, s2 : string) =
    s1 = s2

fun all_except_option (str, lst) = 
    case lst of
	[] => NONE
      | x::xs => case all_except_option (str, xs) of
		     NONE => if same_string (str, x) then SOME(xs) else NONE
		   | SOME(ys) => if same_string (str, x) then SOME(ys) else SOME(x::ys)
		     
(* you may assume that Num is always used with values 2, 3, ..., 10
   though it will not really come up *)
datatype suit = Clubs | Diamonds | Hearts | Spades
datatype rank = Jack | Queen | King | Ace | Num of int 
type card = suit * rank

datatype color = Red | Black
datatype move = Discard of card | Draw 

exception IllegalMove

(* put your solutions for problem 2 here *)
fun get_substitutions1 (substitutions, str) = 
    case substitutions of 
	[] => []
      | x::xs => case all_except_option (str, x) of 
		     NONE => get_substitutions1(xs, str)
		   | SOME(ys) => ys @ get_substitutions1(xs, str)
						      
fun get_substitutions2 (substitutions, str) = 
    let fun tail_get_subst (substitutions, constructed) = 
	    case substitutions of 
		[] => []
	      | x::xs => case all_except_option (str, x) of 
			     NONE => tail_get_subst(xs, constructed)
			   | SOME(ys) => tail_get_subst(xs, ys @ constructed)
    in
	tail_get_subst (substitutions, [])
    end

fun similar_names (substitutions, start_name) = 
    let 
	val {first=f, middle=m, last=l} = start_name
	val variants = get_substitutions1(substitutions, f)
	fun get_full_names (first_names) = 
	    case first_names of 
		[] => []
	      | x::xs => {first=x, middle=m, last=l}::get_full_names(xs)
    in
	get_full_names(f::variants)
    end

fun card_color (card) =
    case card of
	(Spades, _) => Black
      | (Clubs, _) => Black
      | _ => Red

fun card_value(card) = 
    case card of 
	(_, Num(x)) => x
      | (_, Ace) => 11
      | (_, _) => 10

fun remove_card(card_list, card, ex) = 
    case card_list of
	x::xs => if x = card then xs else x::remove_card(xs, card, ex)
      | [] => raise ex

fun all_same_color (cards) = 
    case cards of 
	[] => true
      | x::[] => true
      | x::y::[] => card_color(x)=card_color(y)
      | x::y::ys => card_color(x)=card_color(y) andalso all_same_color(y::ys)

fun sum_cards (cards) = 
    let fun sum_aux(current_cards,current_sum) = 
	    case current_cards of
		[] => current_sum
	      | x::xs => sum_aux(xs, card_value(x) + current_sum)
    in
	sum_aux(cards, 0)
    end

fun score (cards, goal) =
    let 
	val sum = sum_cards (cards)
	val preliminary_score = if sum > goal then 3 * (sum - goal) else goal - sum
    in 
	if all_same_color(cards) then preliminary_score div 2 else preliminary_score
    end
    
fun officiate (cards, moves, goal) = 
    let 
	fun play (held_cards, card_list, current_moves) = 
	    let 
		fun process_move (move, after_moves) = 
		    case move of
			Discard card => play (remove_card(held_cards, card, IllegalMove), card_list, after_moves)
		      | Draw => case card_list of
				    [] => score(held_cards, goal)
				  | x::xs => if sum_cards(held_cards) > goal then
						 score(held_cards, goal)
					     else
						 play (x::held_cards, xs, after_moves)
	    in 
		case current_moves of 
		    [] => score(held_cards, goal)
		  | x::xs => process_move(x, xs)
	    end
    in
	play ([], cards, moves)
    end

