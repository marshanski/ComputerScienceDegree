import * as R from "ramda";

const stringToArray = R.split("");

/* Question 1 */

export const countLetters: (s : string) => {} = 
    R.pipe(
        R.toLower,  
        stringToArray,
        R.filter((x:string) => (x >= 'a' && x <= 'z')), 
        R.countBy(R.identity)
);


const isPairedReduce = (acc: string[], curr: string): string[] =>
{
    if(curr === "{") return R.concat(acc,['}']);
    else if(curr === "[") return R.concat(acc,[']']);
    else if(curr === "(") return R.concat(acc,[')']);

    else if((curr === "}" || curr === "]" || curr === ")")&& R.last(acc) === curr )      
        return R.slice(0,-1,acc);

    else return R.concat(acc,['#']);
}
    
/* Question 2 */
export const isPaired: (s: string) => boolean =
        R.pipe(
            stringToArray,
            R.filter( (x:string)  => x ==='{' || x ==='[' ||x ==='(' || x ==='}' || x ===']' || x ===')'),
            R.reduce(isPairedReduce, []),
            R.isEmpty
        );

/* Question 3 */
export type WordTree = {
    root: string;
    children: WordTree[];
}

export const treeToSentence : (t: WordTree)=> string = (t: WordTree) =>
    t != undefined ?
    t.root +  R.reduce((acc : string ,cur: string )=> acc + " " + cur,"", R.map(treeToSentence,t.children)) : ""


