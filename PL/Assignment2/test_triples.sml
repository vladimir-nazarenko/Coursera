fun test(pair: {name:string, sn:string}) =
    let val {name=x, sn=y} = pair
    in
	{name=x, sn=y}
    end
    
