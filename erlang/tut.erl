-module(tut).
-export([double/1]).
-export([fac/1]).

double(X) ->
	2*X.

fac(1) ->
	1;
fac(N) ->
	N*fac(N-1).

