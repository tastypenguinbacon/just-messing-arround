quicksort :: (Ord a) => [a] -> [a]
quicksort [] = []
quicksort (x:xs) = let smaller = quicksort [a | a <- xs, a <= x]
                       bigger = quicksort [x | a <- xs, a > x]
                   in smaller ++ [x] ++ bigger
main :: IO()
main = do
  print (quicksort [5, 4, 3, 2, 1, 0])
