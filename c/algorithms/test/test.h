#ifndef __TEST_H_
#define __TEST_H_

#include <stdio.h>
#include "colors.h"

#define true 1
#define false 0

typedef char bool;

static void test_case(bool assert) {
  static int test_count = 0;
  if (assert)
    printf(BOLDGREEN "[PASSED]" GREEN " test number: %d\n", test_count);
  else
    printf(BOLDRED "[FAILED]" RED " test number: %d\n", test_count);
  printf(RESET);
  test_count++;
}

#endif
