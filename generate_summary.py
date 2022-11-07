import pathlib
import re
import typing

ROOTDIR = pathlib.Path(__file__).parent
DIFFICULTIES = typing.Literal["easy", "medium", "hard"]


class Problem(typing.NamedTuple):
    """Data class to keep problem information."""
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

problems.sort(key=lambda x: x.path.name)

contents = []
contents.append("# Leetcode\n")
contents.append("| # | Title | Solution | Difficulty |\n")
contents.append("| ------ | ------ | ------ | ------ |\n")

for problem in problems:
    solutions = []
    for sol_path in sorted(problem.path.glob("*")):
        solutions.append(
            f"[{sol_path.name}]({str(sol_path.relative_to(ROOTDIR))})")
    contents.append(
        f"| {problem.number} | {problem.title} | {'</br>'.join(solutions)} | {problem.difficulty} | " + "\n")

with open("README.md", mode="w") as f:
    f.writelines(contents)
