package main

import (
	"fmt"
	"os"
)

func solve() {

	var t int
	fmt.Scan(&t)
	for t > 0 {
		t--
		var n, k int
		fmt.Scan(&n)
		fmt.Scan(&k)
		var s int
		if n <= k {
			s = k
		} else {
			if n%k == 0 {
				s = n
			} else {
				s = (n/k + 1) * k
			}
		}
		var ans int
		if s%n == 0 {
			ans = s / n
		} else {
			ans = s/n + 1
		}
		fmt.Println(ans)
	}
}

func main() {
	if _, ok := os.LookupEnv("ONLINE_JUDGE"); !ok {
		os.Stdin, _ = os.OpenFile("1.in",
			os.O_RDONLY, 0666)
	}
	solve()
}
