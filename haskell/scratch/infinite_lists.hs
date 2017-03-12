fibonacci :: Int -> Integer
fibonacci = (map fib [0 ..] !!)
  where fib 0 = 0
        fib 1 = 1
        fib n = fibonacci (n - 1) + fibonacci (n - 2)

fibList = [fibonacci x | x <- [0, 1 ..]]

main :: IO ()
main = do
  print (take 100 fibList)
