export const dynamic = "force-dynamic";

import BackButton from "@/components/BackButton";
import Container from "@/components/Container";
import PostCard from "@/components/PostCard";
import { readAllPosts } from "@/services/post";
import type { Post } from "@/types/post";

export default async function Blog() {
  const data: Post[] = await readAllPosts();

  return (
    <Container absoluteFooter>
      <BackButton href="/" text="Home" />
      <div className="mt-8">
        <div className="font-bold text-3xl">Blog</div>
        <div className="mt-2 text-sm font-medium text-neutral-400">
          An exploration of my enthusiasm for cinema through reviews, analyses,
          and essays on various films.
        </div>
        <div className="mt-12 flex flex-col gap-4">
          {data.map((post) => (
            <PostCard key={post.id} data={post} />
          ))}
        </div>
      </div>
    </Container>
  );
}
