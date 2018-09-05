int[] function(int userEntry) {
  // all the set up minus the last for loop
  return functionRec(array, 0, userEntry);
}

int[] functionRec(int[] numArray, int currentTerm, int nthTerm){
  if(nthTerm == currentTerm) {
    return numArray
  }
  else if (currentTerm == 0 or currentTerm == 1) {
    numArray[currentTerm] = 1
    return functionRec(numArray, currentTerm++, nthTerm)
  }
  else {
    numArray[currentTerm] = numArray[currentTerm-2] + numArray[currentTerm-1]
    return functionRec(numArray, currentTerm++, nthTerm)
  }
}
