import collections
import pathlib
import re
import typing

ROOTDIR = pathlib.Path(__file__).parent
DIFFICULTIES = typing.Literal["easy", "medium", "hard"]
LANG = {
    "py": "Python",
    "java": "Java",
    "kt": "Kotlin",
}


class Problem(typing.NamedTuple):
    """Data class to store problem information."""
    number: int
    title: str
    path: pathlib.Path
    difficulty: DIFFICULTIES


problems = []

for difficulty in typing.get_args(DIFFICULTIES):
    for path in (ROOTDIR / difficulty).glob("*/"):
        match = re.fullmatch(r"(\d+)-(.*)", path.name)
        assert match is not None
        number, title = match.groups()
        number = int(number)
        title = title.replace("-", " ")
        problems.append(Problem(number, title, path, difficulty))

# Sort by problem title (i.e. problem number)
problems.sort(key=lambda x: x.path.name)

contents = []
contents.append("# Leetcode\n")
contents.append("| # | Title | Solutions | Difficulty |\n")
contents.append("| :------: | ------ | :------: | :------: |\n")

for problem in problems:
    solutions = collections.defaultdict(list)
    for sol_path in sorted(problem.path.glob("*")):
        name, extension = sol_path.name.split(".")
        solutions[name].append(
            f"[{LANG[extension]}]({str(sol_path.relative_to(ROOTDIR))})")

    solutions_str = "</br>".join(
        (f"{name} ({', '.join(elements)})" for name, elements in solutions.items()))
    contents.append(
        f"| {problem.number} | {problem.title} | {solutions_str} | {problem.difficulty} | " + "\n")

with open("README.md", mode="w") as f:
    f.writelines(contents)
