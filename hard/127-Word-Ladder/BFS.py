class Solution:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        numWords = len(wordList)

        def isAdjacent(word1, word2):
            diff = 0
            for c1, c2 in zip(word1, word2):
                if c1 != c2:
                    diff += 1
                if diff > 1:
                    return False
            return True

        queue = deque([beginWord])
        visited = set([beginWord])
        numSteps = 1
        while queue:
            numIter = len(queue)
            for _ in range(numIter):
                crrWord = queue.popleft()
                if crrWord == endWord:
                    return numSteps
                for adjWord in wordList:
                    if adjWord in visited:
                        continue
                    if isAdjacent(crrWord, adjWord):
                        visited.add(adjWord)
                        queue.append(adjWord)
            numSteps += 1
        return 0
